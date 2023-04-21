package shop.mtcoding.tstory.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.tstory.model.post.PostRepository;

@Controller
@RequiredArgsConstructor
public class MainController {

	final private PostRepository postRepository;
    
	@GetMapping({ "main", "/" })
	public String mainForm(Model model, String keyword) {

		// if (keyword == null || keyword.isEmpty()) {
		// 	model.addAttribute("postList", postRepository.findAllAndUsername());
		// } else {
		// 	model.addAttribute("postList", postRepository.findSearchAllPost(keyword));
		// }
		return "mainForm";

	}
}
