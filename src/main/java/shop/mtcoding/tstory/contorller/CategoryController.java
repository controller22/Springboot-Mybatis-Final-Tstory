package shop.mtcoding.tstory.contorller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.tstory.model.category.CategoryRespository;
import shop.mtcoding.tstory.model.post.PostRepository;
import shop.mtcoding.tstory.model.user.User;
import shop.mtcoding.tstory.model.user.UserRepository;

@RequiredArgsConstructor
@Controller
public class CategoryController {
	private final CategoryRespository categoryDao;
	private final PostRepository postDao;
	private final UserRepository userDao;
	private final HttpSession session;

	// 카테고리 등록 페이지
	@GetMapping("/category/writeForm")
	public String writeForm(Model model) {
		User principal = (User) session.getAttribute("principal");
		if (principal == null) {
			return "redirect:/user/loginForm";
		}
		model.addAttribute("principal", principal);
		return "/category/writeForm";
	}

	// 카테고리 등록 응답
	// @PostMapping("/category/write")
	// public String write(String categoryTitle) {
	// 	User principal = (User) session.getAttribute("principal");
	// 	categoryDao.insertCategoryTitle(categoryTitle, principal.getUserId());
	// 	return "redirect:/";
	// }

	// 블로그 카테고리별 게시글 목록 페이지
	@GetMapping("/category/listForm/{categoryId}/{userId}")
	public String listForm(@PathVariable Integer categoryId, @PathVariable Integer userId, Model model, Integer page,
			String keyword) {
		// if (page == null) {
		// 	page = 0;
		// }
		// Integer startNum = page * 5;

		// if (keyword == null || keyword.isEmpty()) {
		// 	PagingDto paging = postDao.pagingByCategory(page, userId, categoryId, null);
		// 	paging.makeBlockInfo();

		// 	model.addAttribute("postList", postDao.findPost(categoryId, userId, startNum, null));
		// 	model.addAttribute("categoryCount", postDao.categoryCount(categoryId, null)); // 카테고리내 게시글 개수
		// 	model.addAttribute("paging", paging); // 페이징
		// 	model.addAttribute("category", categoryDao.findById(categoryId)); // 카테고리 제목 표시
		// 	model.addAttribute("categoryList", categoryDao.findByUserId(userId));
		// 	model.addAttribute("user", userDao.findById(userId)); // 사이드바 카테고리 이동 => 공통
		// } else {

		// 	List<PostAllDto> postList = postDao.findPost(categoryId, userId, startNum, keyword);
		// 	PagingDto paging = postDao.pagingByCategory(page, userId, categoryId, keyword);
		// 	paging.makeBlockInfoByCategoryPostAll(keyword);

		// 	model.addAttribute("postList", postList);
		// 	model.addAttribute("categoryCount", postDao.categoryCount(categoryId, keyword)); // 카테고리내 게시글 개수
		// 	model.addAttribute("paging", paging); // 페이징
		// 	model.addAttribute("category", categoryDao.findById(categoryId)); // 카테고리 제목 표시
		// 	model.addAttribute("categoryList", categoryDao.findByUserId(userId));
		// 	model.addAttribute("user", userDao.findById(userId)); // 사이드바 카테고리 이동 => 공통
		// }

		return "/category/listForm";
	}
}