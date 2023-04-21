package shop.mtcoding.tstory.model.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.tstory.dto.user.CheckDto;
import shop.mtcoding.tstory.dto.user.JoinDto;
import shop.mtcoding.tstory.dto.user.LoginDto;

@Mapper
public interface UserRepository {
    public User findById(Integer userId);

	public List<User> findAll();

	public void insert(JoinDto joinDto);

	public void update(User user);

	public void delete(User user);

    public User findByUsername(String username);

    public CheckDto findByEmail(String email);

    public CheckDto findByNickname(String nickname);

    public User login(LoginDto loginDto);
}
