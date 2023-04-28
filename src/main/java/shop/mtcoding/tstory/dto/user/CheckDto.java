package shop.mtcoding.tstory.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckDto {
	private String username;
	private String password;
	private String email;
	private String nickname;
	private String categoryTitle;
}