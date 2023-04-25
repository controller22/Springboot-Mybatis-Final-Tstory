package shop.mtcoding.tstory.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.tstory.model.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JoinDto {
    private String username;
	private String password;
	private String email;
	private String nickname;

	public User toModel() {
		User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setEmail(email);
		return user;
	}
}