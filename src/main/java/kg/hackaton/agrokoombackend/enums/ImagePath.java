package kg.hackaton.agrokoombackend.enums;

import lombok.Getter;

@Getter
public enum ImagePath {
    PRODUCT("products"),
    USER("users");

    private final String pathToImage;
    ImagePath(String pathToImage) {
        this.pathToImage = pathToImage;
    }
}
