package shop.mtcoding.tstory.model.post;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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