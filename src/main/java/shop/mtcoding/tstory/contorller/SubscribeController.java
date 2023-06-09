package shop.mtcoding.tstory.contorller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.tstory.dto.ResponseDto;
import shop.mtcoding.tstory.model.post.Post;
import shop.mtcoding.tstory.model.user.User;

@RequiredArgsConstructor
@Controller
public class SubscribeController {
	// private final PostDao postDao;
	// private final SubscribeDao subscribeDao;
	// private final SubscribeService subscribeService;
	private final HttpSession session;

	// 구독목록 페이지
	@GetMapping("/subscribe/listForm")
	public String ListForm(Model model) {
		// User principal = (User) session.getAttribute("principal");
		// if (principal == null) {
		// 	return "redirect:/user/loginForm";
		// }
		// List<Post> postList = postDao.findByUserId(principal.getUserId());
		// model.addAttribute("postList", postList);
		// List<Subscribe> subscribeList = subscribeDao.findByUserId(principal.getUserId());
		// model.addAttribute("subscribeList", subscribeList);
		return "subscribe/listForm";
	}

	// 구독 응답
	// @PostMapping("/s/api/subscribe/{usersId}")
	// public @ResponseBody ResponseDto<Integer> companySubscribe(@PathVariable Integer usersId, Model model) {
	// 	User principal = (User) session.getAttribute("principal");
	// 	Integer subscribeId = subscribeService.구독Id불러오기(principal.getUserId(), usersId);

	// 	if (subscribeId == null) {
	// 		subscribeService.구독하기(principal.getUserId(), usersId);
	// 		subscribeId = subscribeService.구독Id불러오기(principal.getUserId(), usersId);
	// 		return new CMRespDto<Integer>(1, "구독 완료", subscribeId);
	// 	}

	// 	subscribeService.구독취소(subscribeId);
	// 	return new ResponseDto<Integer>(1, "구독 취소 완료", null);
	// }

}
