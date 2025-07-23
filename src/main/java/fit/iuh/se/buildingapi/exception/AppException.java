package fit.iuh.se.buildingapi.exception;

import fit.iuh.se.buildingapi.entity.enums.HttpCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AppException extends RuntimeException {
    private HttpCode httpCode;
    public AppException(HttpCode httpCode) {
        this.httpCode = httpCode;
    }
}
