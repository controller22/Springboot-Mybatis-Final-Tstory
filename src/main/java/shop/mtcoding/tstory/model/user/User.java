package shop.mtcoding.tstory.model.user;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Integer userId;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private String profileImg;
	private Timestamp updatedAt;
	private Timestamp createdAt;
	
	public User(String username, String password, String nickname, String email) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
	}
}
