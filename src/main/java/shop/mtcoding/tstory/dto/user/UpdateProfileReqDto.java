package shop.mtcoding.tstory.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileReqDto {
	private Integer userId;
	private String nickname;
	private String nicknameUpdate;
	private String profileImg;
}
