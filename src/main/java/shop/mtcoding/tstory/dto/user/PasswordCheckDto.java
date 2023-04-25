package shop.mtcoding.tstory.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordCheckDto {
    private String password;
    private Integer userId;

}
