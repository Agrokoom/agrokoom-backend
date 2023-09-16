package kg.hackaton.agrokoombackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NoAccessException extends RuntimeException{
    public NoAccessException(String msg){
        super(msg);
    }
}
