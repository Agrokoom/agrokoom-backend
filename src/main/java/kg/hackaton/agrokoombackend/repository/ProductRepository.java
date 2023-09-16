package kg.hackaton.agrokoombackend.repository;

import kg.hackaton.agrokoombackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
