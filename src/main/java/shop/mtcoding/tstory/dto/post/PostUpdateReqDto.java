package shop.mtcoding.tstory.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class PostUpdateReqDto {
	private Integer postId;
	// private Integer categoryId;
	// private String categoryTitle;
	private String postTitle;
	private String postContent;
	// private String postThumnail;
	private Integer userId;
	// private String noFile; // 썸네일 수정 안할때!

	public PostUpdateReqDto toModel(int principalId) {
		PostUpdateReqDto postUpdateDto = new PostUpdateReqDto();
        postUpdateDto.setPostContent(postContent);
        postUpdateDto.setPostId(postId);
        postUpdateDto.setPostTitle(postTitle);
        postUpdateDto.setUserId(principalId);
		return postUpdateDto;
	}
}