package shop.mtcoding.tstory.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.tstory.dto.user.CheckDto;
import shop.mtcoding.tstory.dto.user.JoinDto;
import shop.mtcoding.tstory.model.category.CategoryRespository;
import shop.mtcoding.tstory.model.user.User;
import shop.mtcoding.tstory.model.user.UserRepository;
import shop.mtcoding.tstory.util.SHA256;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final HttpSession session;
	// private final JavaMailSender mailSender;
	private final CategoryRespository categoryRespository;
	private final SHA256 sha256;

	// @Transactional
	public void 회원가입(JoinDto joinDto) {
		String encPassword = sha256.encrypt(joinDto.getPassword());
		joinDto.setPassword(encPassword); // 회원가입으로 받은 비밀번호 암호화
		userRepository.insert(joinDto);
		System.out.println("디버깅11 : ");
	}

	public boolean 유저네임중복확인(String username) {
		User usersPS = userRepository.findByUsername(username);

		if (usersPS == null) { // 중복 안됨
			return false;
		} else { // 중복됨
			return true;
		}
	}

	public boolean 이메일중복확인(String email) {
		CheckDto usersPS = userRepository.findByEmail(email);

		if (usersPS == null) { // 중복 안됨
			return false;
		} else { // 중복됨
			return true;
		}
	}

	public boolean 닉네임중복확인(String nickname) {
		CheckDto usersPS = userRepository.findByNickname(nickname);

		if (usersPS == null) { // 중복 안됨
			return false;
		} else { // 중복됨
			return true;
		}
	}

    public boolean 카테고리명중복확인(String categoryTitle, Integer userId) {
		System.out.println("디버그");
		CheckDto categoryPS = categoryRespository.findByCategoryTitle(categoryTitle, userId);
		System.out.println("디버그");
		if (categoryPS == null) { // 중복 안됨
			return false;
		} else { // 중복됨
			return true;
		}
    }
}
