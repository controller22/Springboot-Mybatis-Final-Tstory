package shop.mtcoding.tstory.contorller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.tstory.dto.ResponseDto;
import shop.mtcoding.tstory.dto.category.InsertCategoryTitleReqDto;
import shop.mtcoding.tstory.dto.user.CheckDto;
import shop.mtcoding.tstory.model.category.CategoryRepository;
import shop.mtcoding.tstory.model.post.PostRepository;
import shop.mtcoding.tstory.model.user.User;
import shop.mtcoding.tstory.model.user.UserRepository;

@RequiredArgsConstructor
@Controller
public class CategoryController {
	private final CategoryRepository categoryRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final HttpSession session;

	// 카테고리 등록 페이지
	@GetMapping("/category/writeForm")
	public String writeForm(Model model) {
		User principal = (User) session.getAttribute("principal");
		if (principal == null) {
			return "redirect:/loginForm";
		}
		model.addAttribute("principal", principal);
		model.addAttribute("user", userRepository.findById(principal.getUserId()));
		model.addAttribute("userImg", userRepository.findById(principal.getUserId()));
	
		return "/category/writeForm";
	}

	// 카테고리 등록 응답
	@PostMapping("/category/write")
	public @ResponseBody ResponseDto<?> write(@RequestBody InsertCategoryTitleReqDto insertCategoryTitleReqDto, Model model) {
		
		User principal = (User) session.getAttribute("principal");
		if (principal != null) {
			model.addAttribute("user", userRepository.findById(principal.getUserId()));
		}
		
		System.out.println("디버그 123 :"+principal.getUserId());
		CheckDto categoryPS = categoryRepository.findByCategoryTitle(insertCategoryTitleReqDto.getCategoryTitle(), 
		principal.getUserId());
		System.out.println("디버그 2 :  "+insertCategoryTitleReqDto.getCategoryTitle());
		
		if (categoryPS != null) {
			return new ResponseDto<>(-1, "실패", null);	
		}
		categoryRepository.insertCategoryTitle(insertCategoryTitleReqDto.getCategoryTitle(), 
		insertCategoryTitleReqDto.getUserId());
		return new ResponseDto<>(1, "성공", null);
	}


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