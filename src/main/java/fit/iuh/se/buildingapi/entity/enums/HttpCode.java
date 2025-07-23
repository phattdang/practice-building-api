package fit.iuh.se.buildingapi.entity.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum HttpCode {
    OK(200, "OK!"),
    CREATED(201, "Created Successfully!"),
    BAD_REQUEST(400, ""),
    NOT_FOUND(404, "Not found!");
    final int CODE;
    final String MESSAGE;

    HttpCode(int code, String message) {
        this.CODE = code;
        this.MESSAGE = message;
    }
}
