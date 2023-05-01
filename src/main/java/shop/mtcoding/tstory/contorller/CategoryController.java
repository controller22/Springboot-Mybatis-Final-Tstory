package shop.mtcoding.tstory.contorller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.tstory.dto.ResponseDto;
import shop.mtcoding.tstory.dto.category.InsertCategoryTitleReqDto;
import shop.mtcoding.tstory.dto.category.UpdateCategoryTitleReqDto;
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
	public String listForm(@PathVariable Integer categoryId, @PathVariable Integer userId, Model model) {
		System.out.println("디버그 : "+userId );
		System.out.println("디버그 : "+categoryId );
		User principal = (User) session.getAttribute("principal");
		if (principal != null) {
			model.addAttribute("user", userRepository.findById(principal.getUserId()));
		}
		model.addAttribute("postList", postRepository.findPost(categoryId, userId));
		model.addAttribute("category", categoryRepository.findById(categoryId)); // 카테고리 제목 표시
		model.addAttribute("categoryList", categoryRepository.findByUserId(userId));
		model.addAttribute("user", userRepository.findById(userId)); // 사이드바 카테고리 이동 => 공통
		
		return "/category/listForm";
	}

	@DeleteMapping("/category/{categoryId}")
		public @ResponseBody ResponseDto<?> delete(@PathVariable Integer categoryId) {
		categoryRepository.delete(categoryId);
		return new ResponseDto<>(1, "게시글 삭제 성공", null);
	}

	// 카테고리 수정 페이지
	@GetMapping("/category/updateForm/{categoryId}")
	public String updateForm(Model model, @PathVariable Integer categoryId) {
		User principal = (User) session.getAttribute("principal");
		model.addAttribute("user", userRepository.findById(principal.getUserId()));
		model.addAttribute("category", categoryRepository.findByCategoryTitleId(categoryId, principal.getUserId()));
		return "/category/updateForm";
	}

	// 카테고리명 수정 응답
	@PutMapping("/user/categoryTitle")
	public @ResponseBody ResponseDto<?> update(@RequestBody UpdateCategoryTitleReqDto updateCategoryTitleDto) {
		categoryRepository.updateCategoryTitle(updateCategoryTitleDto.getCategoryTitle(), updateCategoryTitleDto.getUserId(),
				updateCategoryTitleDto.getCategoryId());

		return new ResponseDto<>(1, "성공", null);
	} 
}