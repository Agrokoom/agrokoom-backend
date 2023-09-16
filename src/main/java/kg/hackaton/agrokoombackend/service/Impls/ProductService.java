package kg.hackaton.agrokoombackend.service.Impls;


import kg.hackaton.agrokoombackend.enums.ImagePath;
import kg.hackaton.agrokoombackend.exception.ProductNotFoundException;
import kg.hackaton.agrokoombackend.model.Product;
import kg.hackaton.agrokoombackend.repository.ProductRepository;
import kg.hackaton.agrokoombackend.service.ImageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductService {
    ProductRepository productRepository;
    ImageService imageService;

    public Page<Product> fetchProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product fetchProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id %d has not been found".formatted(id)));
    }

    public Long saveProduct(Product product) {
        return productRepository.save(product).getId();
    }

    public ResponseEntity<String> saveProductWithImage(Long id, MultipartFile file) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id %d has not been found".formatted(id)));
        product.getImages().add(imageService.uploadImage(file, ImagePath.PRODUCT));
        productRepository.save(product);
        return ResponseEntity.ok("Продукт успешно внесен в базу данных");
    }

    public ResponseEntity<String> saveProductWithImages(Long id, MultipartFile[] files) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id %d has not been found".formatted(id)));
        product.setImages(imageService.uploadImages(files));
        productRepository.save(product);
        return ResponseEntity.ok("Продукт успешно внесен в базу данных");
    }

    public Page<Product> search(String keyword, Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
