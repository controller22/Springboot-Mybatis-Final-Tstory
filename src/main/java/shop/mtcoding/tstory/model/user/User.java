package shop.mtcoding.tstory.model.user;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
	private Integer userId;
	private String username;
	private String password;
	private String email;
	private String profileImg;
	private Timestamp updatedAt;
	private Timestamp createdAt;
}
