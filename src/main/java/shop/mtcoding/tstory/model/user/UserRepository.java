package shop.mtcoding.tstory.model.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.tstory.dto.user.CheckDto;
import shop.mtcoding.tstory.dto.user.JoinDto;
import shop.mtcoding.tstory.dto.user.LoginDto;
import shop.mtcoding.tstory.dto.user.UserUpdateDto;

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

    public User findByPasswordAndUserId(@Param("password") String password,@Param("userId") Integer userId);

    public void updateById(@Param("passwordUpdate") String passwordUpdate, @Param("email") String email, @Param("userId") Integer userId);

    public void delete(Integer userId);

    public void updateByPassword(@Param("passwordUpdate") String passwordUpdate, @Param("userId") Integer userId);

    public void updateByNickname(@Param("nickname") String nickname,@Param("userId")  Integer userId);

    public List<User> findAllMember();

    public List<User> findAllAdmin();
}
