package shop.mtcoding.tstory.contorller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.tstory.model.post.PostRepository;
import shop.mtcoding.tstory.model.user.User;
import shop.mtcoding.tstory.model.user.UserRepository;


@RequiredArgsConstructor
@Controller
public class MainController {
	private final HttpSession session;
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	// 메인 페이지
	@GetMapping({ "/main/mainForm", "/" })
	public String mainForm(Model model, String keyword) {
		User principal = (User) session.getAttribute("principal");
		if (principal != null) {
			model.addAttribute("userImg", userRepository.findById(principal.getUserId()));
			model.addAttribute("user", userRepository.findById(principal.getUserId()));
		}

		if (keyword == null || keyword.isEmpty()) {
			model.addAttribute("postList", postRepository.findAllAndUsername());
		} else {
			model.addAttribute("postList", postRepository.findSearchAllPost(keyword));
		}
		return "mainForm";

	}
}
