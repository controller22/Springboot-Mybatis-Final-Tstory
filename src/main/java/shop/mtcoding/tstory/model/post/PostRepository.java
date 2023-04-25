package shop.mtcoding.tstory.model.post;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.tstory.dto.main.KeywordRespDto;
import shop.mtcoding.tstory.dto.post.PostAllRespDto;


@Mapper
public interface PostRepository {
    public Post findById(Integer postId);

	public List<Post> findAll();

	public void insert(Post post);

	public void update(Post post);

	public void delete(Post post);

    public List<PostAllRespDto> findAllAndUsername();

    public List<KeywordRespDto> findSearchAllPost(String keyword);
}
