package shop.mtcoding.tstory.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSaveReqDto {
    private String postTitle;
	private String postContent;
	// private String postThumnail;
	private Integer userId;
	private Integer categoryId;    
}
