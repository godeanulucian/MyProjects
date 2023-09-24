package lucian.ehealth.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
@Component
public class RequestHandler {
    public interface ReturnCodeAware { // specifies that T must be a type that implements the ReturnCodeAware interface, ensuring that the setReturnCode method is available
        void setReturnCode(String returnCode);
    }

    // BAD REQUEST HANDLER
    public <T extends ReturnCodeAware> ResponseEntity<?> handleBadRequest(String returnCode, T dtoResponse) {
        dtoResponse.setReturnCode(returnCode);
        return new ResponseEntity<>(dtoResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // UNAUTHORIZED REQUEST HANDLER
    public <T extends ReturnCodeAware> ResponseEntity<?> handleUnauthorizedRequest(T dtoResponse) {
        dtoResponse.setReturnCode("You are not allowed to do this");
        return new ResponseEntity<>(dtoResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

}
