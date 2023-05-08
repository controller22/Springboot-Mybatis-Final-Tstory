package shop.mtcoding.tstory.model.post;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Post {
	private Integer postId;
	private String postTitle;
	private String postContent;
	private String postThumnail;
	private Integer userId;
	private Integer categoryId;
	private Timestamp updatedAt;
	private Timestamp createdAt;
}