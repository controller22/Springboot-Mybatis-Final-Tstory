package shop.mtcoding.tstory.model.post;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.tstory.dto.admin.PostHistoryRespDto;
import shop.mtcoding.tstory.dto.main.KeywordRespDto;
import shop.mtcoding.tstory.dto.paging.PagingRespDto;
import shop.mtcoding.tstory.dto.post.PostAllRespDto;
import shop.mtcoding.tstory.dto.post.PostDetailDto;
import shop.mtcoding.tstory.dto.post.PostSaveReqDto;
import shop.mtcoding.tstory.dto.post.PostUpdateReqDto;


@Mapper
public interface PostRepository {
    public Post findById(Integer postId);

	public List<Post> findAll();

	public void insert(Post post);

	public void update(PostUpdateReqDto postUpdateReqDto);

	public void delete(int postId);

    public List<PostAllRespDto> findAllAndUsername();

    public List<KeywordRespDto> findSearchAllPost(String keyword);

    public PostDetailDto findByIdAndUser(@Param("postId") Integer postId,@Param("userId")  Integer userId);

    public void insertSave(PostSaveReqDto postSaveReqDto);

    public PostUpdateReqDto findByIdUpdate(@Param("postId") Integer postId, @Param("userId") Integer userId);

    public List<PostAllRespDto> findAllPost(@Param("userId") Integer userId,@Param("keyword") String keyword,
    @Param("startNum") Integer startNum);

    public List<PostAllRespDto> findPost(@Param("categoryId") Integer categoryId,@Param("userId") Integer userId);

    public List<PostHistoryRespDto> findAllHistory();

    public PagingRespDto paging(@Param("page") Integer page, @Param("userId") Integer userId,
    @Param("keyword") String keyword);

    public Post postCount(@Param("userId") Integer userId, @Param("keyword") String keyword);


}
