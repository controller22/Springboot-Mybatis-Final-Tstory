package shop.mtcoding.tstory.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.tstory.model.post.Post;

@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class PostUpdateReqDto {
	private Integer postId;
	private Integer categoryId;
	private String categoryTitle;
	private String postTitle;
	private String postContent;
	private String postThumnail;
	private Integer userId;
	private String noFile; // 썸네일 수정 안할때!

	public Post toModel(int principalId) {
		Post postUpdateDto = new Post();
        postUpdateDto.setPostId(postId);
        postUpdateDto.setCategoryId(categoryId);
        postUpdateDto.setPostTitle(postTitle);
        postUpdateDto.setPostContent(postContent);
        postUpdateDto.setPostThumnail(postThumnail);
        postUpdateDto.setUserId(principalId);
		return postUpdateDto;
	}
}