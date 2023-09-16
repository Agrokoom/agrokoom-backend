package kg.hackaton.agrokoombackend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UnsupportedImageTypeException extends RuntimeException{

    public UnsupportedImageTypeException(String message) {
        super(message);
    }
}
