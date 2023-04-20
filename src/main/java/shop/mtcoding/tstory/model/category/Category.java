package shop.mtcoding.tstory.model.category;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
	private Integer usersId;
	private String username;
	private String password;
	private String email;
	private String profileImg;
	private Timestamp updatedAt;
	private Timestamp createdAt;
}
