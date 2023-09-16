package kg.hackaton.agrokoombackend.service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kg.hackaton.agrokoombackend.enums.ImagePath;
import kg.hackaton.agrokoombackend.exception.UnsupportedImageTypeException;
import kg.hackaton.agrokoombackend.model.User;
import kg.hackaton.agrokoombackend.repository.UserRepository;
import lombok.SneakyThrows;
import org.apache.http.entity.ContentType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Service
public class ImageService {
    private final UserRepository userRepository;
    private final String cloudinaryUrl = "cloudinary://381336617786497:hvkn87CD5_CcoBU3g6m1FSwHo-I@agrokoom";
    private final Cloudinary cloudinary = new Cloudinary((cloudinaryUrl));

    public ImageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SneakyThrows
    public List<String> uploadImages(MultipartFile[] files) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            checkImage(file);
            var upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                    "folder", ImagePath.PRODUCT.getPathToImage(),
                    "public_id", getRandomUUID(),
                    "unique_filename", "true"
            ));
            urls.add((String) upload.get("secure_url"));
        }
        return urls;
    }

    @SneakyThrows
    public ResponseEntity<String> saveForUser(MultipartFile file, User user) {
        checkImage(file);
        var upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "folder", ImagePath.USER.getPathToImage(),
                "public_id", getRandomUUID(),
                "unique_filename", "true"
        ));
        user.setImageUrl((String) upload.get("secure_url"));
        userRepository.save(user);

        return ResponseEntity.ok("Фотография сохранена");
    }

    @SneakyThrows
    public ResponseEntity<String> saveRegistrationCertificate(MultipartFile file, User user) {
        checkImage(file);
        var upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "folder", ImagePath.USER,
                "public_id", getRandomUUID(),
                "unique_filename", "true"
        ));
        user.setRegistrationCertificateUrl((String) upload.get("secure_url"));
        userRepository.save(user);

        return ResponseEntity.ok("Свидетельство о регистрации сохранено");
    }

    @SneakyThrows
    public String uploadImage(MultipartFile file, ImagePath path) {
        checkImage(file);
        return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "folder",path.getPathToImage(),
                "public_id", getRandomUUID(),
                "unique_filename", "true"
        )).get("secure_url");
    }

    public void checkImage(MultipartFile file) {
        if (!Arrays.asList(ContentType.IMAGE_GIF.getMimeType(),
                        ContentType.IMAGE_JPEG.getMimeType(),
                        ContentType.IMAGE_PNG.getMimeType(),
                        ContentType.IMAGE_SVG.getMimeType()).
                contains(file.getContentType()))
            throw new UnsupportedImageTypeException("Image type of %s is not supported".formatted(file.getContentType()));
        if (file.isEmpty()) throw new UnsupportedImageTypeException("Image %s must not be empty".formatted(file.getOriginalFilename()));
    }

    public String getRandomUUID() {
        String uniqueUUID =""+ UUID.randomUUID() + UUID.randomUUID() + UUID.randomUUID();
        return uniqueUUID.replaceAll("[^a-zA-z0-9]","");
    }



}
