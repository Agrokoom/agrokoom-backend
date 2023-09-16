package kg.hackaton.agrokoombackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.hackaton.agrokoombackend.model.Product;
import kg.hackaton.agrokoombackend.model.User;
import kg.hackaton.agrokoombackend.service.Impls.ProductService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(
        name = "Контроллер для работы с продуктами",
        description = "В этом контроллере есть возможности получения, добавления продуктов"
)
public class ProductController {
    ProductService productService;

    @GetMapping("/all")
    @Operation(
            summary = "Получение всех продуктов"
    )
    public Page<Product> getAllProducts(@PageableDefault Pageable pageable){
        return productService.fetchProducts(pageable);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение продукта по айди"
    )
    public Product getProductById(@PathVariable Long id){
        return productService.fetchProductById(id);
    }

    @PostMapping("/add")
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Добавление продукта"
    )
    public ResponseEntity<Long> addProduct(@RequestBody Product product,
                                           @AuthenticationPrincipal User user){
        return productService.saveProduct(product, user);
    }

    @PostMapping(value = "/add-image/{product_id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Добавление фотографии продукта"
    )
    public ResponseEntity<String> saveImageForProduct(@PathVariable Long product_id,
                                                      @RequestPart MultipartFile file){
        return productService.saveProductWithImage(product_id, file);
    }

    @PostMapping(value = "/add-images/{product_id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SecurityRequirement(name = "JWT")
    @Operation(
            summary = "Добавление фотографий продукта"
    )
    public ResponseEntity<String> saveImagesForProduct(@PathVariable Long product_id,
                                                      @RequestPart MultipartFile[] files){
        return productService.saveProductWithImages(product_id, files);
    }
}
