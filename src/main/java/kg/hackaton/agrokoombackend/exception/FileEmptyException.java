package kg.hackaton.agrokoombackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FileEmptyException extends RuntimeException{
    public FileEmptyException(String msg){
        super(msg);
    }
}
