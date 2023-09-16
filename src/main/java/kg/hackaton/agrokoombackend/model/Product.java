package kg.hackaton.agrokoombackend.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity{

    @Column(nullable = false)
    String category;

    @Column(nullable = false)
    String name;

    @Column(name = "vendor_code",unique = true,nullable = false)
    @JsonProperty("vendor_code")
    String vendorCode;

    @Column(name = "current_price",nullable = false)
    @JsonProperty("current_price")
    Integer currentPrice;

    @Column(name = "previous_price",nullable = false)
    @JsonProperty("previous_price")
    Integer previousPrice;

    @Column(name = "box_weight",nullable = false)
    @JsonProperty("box_weight")
    Integer boxWeight;

    @Column(nullable = false)
    String type;

    @Column(nullable = false)
    String sort;

    @Column(nullable = false)
    String season;

    @Column(name = "key_words",nullable = false)
    @JsonProperty("product_key_words")
    @ElementCollection(targetClass=String.class)
    List<String> keyWords = new ArrayList<>();

    @Column(name = "image_urls")
    @JsonProperty("product_images")
    @ElementCollection(targetClass=String.class)
    List<String> images = new ArrayList<>();

    @Column(name = "commerce_type",nullable = false)
    @JsonProperty("commerce_type")
    String commerceType;

}
