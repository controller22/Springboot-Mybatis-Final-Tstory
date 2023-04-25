package shop.mtcoding.tstory.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUpdateDto {
	private Integer userId;
	private String passwordUpdate;
	private String email;
}