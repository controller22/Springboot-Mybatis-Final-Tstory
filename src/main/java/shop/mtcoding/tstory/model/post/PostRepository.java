package shop.mtcoding.tstory.model.post;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostRepository {
    public Post findById(Integer postId);

	public List<Post> findAll();

	public void insert(Post post);

	public void update(Post post);

	public void delete(Post post);
}
