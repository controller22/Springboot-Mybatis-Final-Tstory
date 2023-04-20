package shop.mtcoding.tstory.model.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    public User findById(Integer userId);

	public List<User> findAll();

	public void insert(User user);

	public void update(User user);

	public void delete(User user);
}
