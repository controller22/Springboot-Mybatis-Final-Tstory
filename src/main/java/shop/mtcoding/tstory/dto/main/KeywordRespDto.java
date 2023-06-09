package shop.mtcoding.tstory.dto.main;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KeywordRespDto {
	private Integer postId;
	private String postTitle;
	private String username;
	private String nickname;
	private Integer userId;
	private String postThumnail;
	private Integer categoryId;
	private Timestamp updatedAt;
	private Timestamp createdAt;
}
