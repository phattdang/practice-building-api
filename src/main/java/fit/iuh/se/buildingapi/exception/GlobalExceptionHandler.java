package fit.iuh.se.buildingapi.exception;

import fit.iuh.se.buildingapi.dto.response.ApiResponse;
import fit.iuh.se.buildingapi.entity.enums.HttpCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NullPointerException.class)
    ResponseEntity<ApiResponse> handlingNullPointerException(NullPointerException exception){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(HttpCode.BAD_REQUEST.getCODE());
        apiResponse.setMessage(exception.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        ApiResponse apiResponse = new ApiResponse();

        String errorKey = exception.getFieldError().getDefaultMessage();
        HttpCode httpCode = HttpCode.valueOf(errorKey);

        apiResponse.setCode(httpCode.getCODE());
        apiResponse.setMessage(httpCode.getMESSAGE());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ApiResponse apiResponse = new ApiResponse();

        HttpCode httpCode = exception.getHttpCode();

        apiResponse.setCode(httpCode.getCODE());
        apiResponse.setMessage(httpCode.getMESSAGE());

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
