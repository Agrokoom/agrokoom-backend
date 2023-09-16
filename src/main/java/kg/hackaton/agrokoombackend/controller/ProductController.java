package kg.hackaton.agrokoombackend.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import kg.hackaton.agrokoombackend.model.Product;
import kg.hackaton.agrokoombackend.model.User;
import kg.hackaton.agrokoombackend.service.Impls.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(
        name = "Контроллер для работы с продуктами",
        description = "В этом контроллере есть возможности добавления,удаления продукта"
)
public class ProductController {

    ProductService productService;

    @PostMapping
    public ResponseEntity<Long> saveProduct(@RequestBody Product product, @AuthenticationPrincipal User user) {
        return productService.saveProduct(product,user);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> saveProductImageById(@PathVariable Long id, @RequestParam(name = "file") MultipartFile file) {
        return productService.saveProductWithImage(id,file);
    }

}
