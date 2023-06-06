package shop.mtcoding.tstory.contorller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.tstory.dto.ResponseDto;
import shop.mtcoding.tstory.dto.main.HeaderRespDto;
import shop.mtcoding.tstory.dto.paging.PagingRespDto;
import shop.mtcoding.tstory.dto.post.PostAllRespDto;
import shop.mtcoding.tstory.dto.post.PostDetailDto;
import shop.mtcoding.tstory.dto.post.PostSaveReqDto;
import shop.mtcoding.tstory.dto.post.PostUpdateReqDto;
import shop.mtcoding.tstory.model.category.Category;
import shop.mtcoding.tstory.model.category.CategoryRepository;
import shop.mtcoding.tstory.model.post.PostRepository;
import shop.mtcoding.tstory.model.user.User;
import shop.mtcoding.tstory.model.user.UserRepository;
import shop.mtcoding.tstory.service.PostService;
import shop.mtcoding.tstory.service.SubscribeService;

@RequiredArgsConstructor
@Controller
public class PostController {
	private final HttpSession session;
	private final PostService postService;
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final SubscribeService subscribeService;

	// 게시글 수정하기 페이지
	@GetMapping("/api/post/updateForm/{postId}")
	public String updateForm( @PathVariable Integer postId, Model model) {
		User principal = (User) session.getAttribute("principal");
		if (principal == null) {
			return "redirect:/loginForm";
		}
		PostUpdateReqDto postUpdateDto = postRepository.findByIdUpdate(postId, principal.getUserId());
		// List<HeaderDto> titleDto = categoryDao.findByUserId(principal.getUserId());
		model.addAttribute("post", postUpdateDto);
		// model.addAttribute("titleList", titleDto);
		return "/post/updateForm";
	}

	// 게시글 수정 응답
	@PutMapping("/api/post/update")
	public @ResponseBody ResponseDto<?> update(
		@RequestPart ("file") MultipartFile file,
		@RequestPart("postUpdateReqDto") PostUpdateReqDto postUpdateReqDto) throws Exception {
		
		User principal = (User) session.getAttribute("principal");
		postService.게시글수정하기(postUpdateReqDto,principal.getUserId(),file);
		// model.addAttribute("userId", principal.getUserId());
		return new ResponseDto<>(1, "게시글 수정성공", null);
	}

	// 썸네일 없는 게시글 수정 응답
	@PutMapping("/api/post/update/noImg")
	public @ResponseBody ResponseDto<?> updateNoImg(@RequestBody PostUpdateReqDto postUpdateReqDto) {
		System.out.println("디버그 getNoFile : " + postUpdateReqDto.getNoFile());
		User principal = (User) session.getAttribute("principal");
		if (postUpdateReqDto.getNoFile() == null) {
			System.out.println("디버그 : 원래 썸네일없");
			postService.원래썸네일없는게시글수정하기(postUpdateReqDto, principal.getUserId());
		}
		postService.썸네일없는게시글로수정하기(postUpdateReqDto, principal.getUserId());
		return new ResponseDto<>(1, "썸네일없는게시글 수정 성공", null);
	}

	// 게시글 등록 페이지
	@GetMapping("/api/post/writeForm")
	public String writeForm(Model model) {
		User principal = (User) session.getAttribute("principal");
		if (principal == null) {
			return "redirect:/loginForm";
		}
		List<HeaderRespDto> titleDto = categoryRepository.findByUserId(principal.getUserId());
		List<Category> categoryPS = categoryRepository.findByUser(principal.getUserId());
		if (categoryPS.size() == 0) {
			return "redirect:/category/writeForm";
		}
		
		model.addAttribute("user", userRepository.findById(principal.getUserId()));
		model.addAttribute("userImg", userRepository.findById(principal.getUserId()));
		model.addAttribute("titleList", titleDto);
		return "/post/writeForm";
	}

	// 게시글 등록 응답
	@PostMapping("/api/post/write")
	public @ResponseBody ResponseDto<?> write(@RequestPart("file") MultipartFile file,
			@RequestPart("postSaveReqDto") PostSaveReqDto postSaveReqDto) throws Exception {
		
			System.out.println("디버그 : ");

		User principal = (User) session.getAttribute("principal");
		postService.게시글등록하기(postSaveReqDto, principal.getUserId(), file);
		return new ResponseDto<>(1, "게시글 등록 성공", null);
	}

	@PostMapping("/api/post/write/noImg")
	public @ResponseBody ResponseDto<?> writeNoImg(@RequestBody PostSaveReqDto postSaveReqDto) {
		System.out.println("디버그 : "+postSaveReqDto.getCategoryId());
		User principal = (User) session.getAttribute("principal");
		postService.썸네일없는게시글등록하기(postSaveReqDto, principal.getUserId());
		return new ResponseDto<>(1, "이미지없는 게시글 등록 성공", null);
	}





	// 블로그 전체 게시글 목록 페이지
	@GetMapping("/post/listForm/{userId}")
	public String list(@PathVariable Integer userId, @RequestParam(name="page", required = false) Integer page, Model model,
	@RequestParam(name="keyword", required = false) String keyword) {

		User principal = (User) session.getAttribute("principal");
		
		if (page == null) {
			page = 0;
		}

		Integer startNum = page * 5;
		
		
		if (keyword == null || keyword.isEmpty()) {
			PagingRespDto paging = postRepository.paging(page, userId, null);
			
			paging.makeBlockInfo();

			model.addAttribute("postCount", postRepository.postCount(userId, null)); // 전체게시글 개수
			model.addAttribute("paging", paging); // 페이징
			model.addAttribute("postList",postRepository.findAllPost(userId,null, startNum));
			model.addAttribute("categoryList", categoryRepository.findByUserId(userId)); 
			
			model.addAttribute("user", userRepository.findById(userId));
			if (principal != null) {
				model.addAttribute("userImg", userRepository.findById(principal.getUserId()));
			}
		}

		if (principal != null) {
			System.out.println("디버그1 : " + page);
			List<PostAllRespDto> postList = postRepository.findAllPost(principal.getUserId(), keyword,startNum);
			model.addAttribute("postList", postList); // 블로그 전체게시글
			
			PagingRespDto paging = postRepository.paging(page, userId, keyword);
			paging.makeBlockInfoByPostAll(keyword);
			model.addAttribute("postCount", postRepository.postCount(userId, keyword)); // 전체게시글 개수
			model.addAttribute("paging", paging); // 페이징 
			model.addAttribute("categoryList", categoryRepository.findByUserId(userId)); // 사이드바 카테고리 이동 => 공통
			model.addAttribute("user", userRepository.findById(userId));
			model.addAttribute("userImg", userRepository.findById(principal.getUserId()));
			
		}

		else {
			List<PostAllRespDto> postList = postRepository.findAllPost(userId, keyword,startNum);
			model.addAttribute("postList", postList); // 블로그 전체게시글
			PagingRespDto paging = postRepository.paging(page, userId, keyword);
			paging.makeBlockInfoByPostAll(keyword);
			model.addAttribute("postCount", postRepository.postCount(userId, keyword)); // 전체게시글 개수
			model.addAttribute("paging", paging); // 페이징
			model.addAttribute("categoryList", categoryRepository.findByUserId(userId)); // 사이드바 카테고리 이동 => 공통
		}


		return "/post/listForm";
	}


	// 게시글 상세보기 페이지
	@GetMapping("/post/detailForm/{postId}/{userId}")
	public String detailForm(@PathVariable Integer postId, @PathVariable Integer userId, Model model) {
		User principal = (User) session.getAttribute("principal");

		// 좋아요 화면에 넣는용도
		if (principal == null) {
			// visitDao.countByVisit(userId, postId);
			PostDetailDto postDetail = postRepository.findByIdAndUser(postId, null);
			model.addAttribute("post", postDetail);
			model.addAttribute("user", userRepository.findById(userId));
			// model.addAttribute("categoryList", categoryRespository.findByUserId(userId)); // 사이드바 카테고리
			// model.addAttribute("postList", postRepository.findByUserId(userId)); // 블로그 전체게시글
			// model.addAttribute("visit", visitory.findByVisitCount(userId));
		} else {
			// visitDao.countByVisit(userId, postId);
			PostDetailDto postDetail = postRepository.findByIdAndUser(postId, principal.getUserId());
			model.addAttribute("post", postDetail);
			if (principal != null) {
				model.addAttribute("userImg", userRepository.findById(principal.getUserId()));
			}
			model.addAttribute("user", userRepository.findById(userId));
			// model.addAttribute("categoryList", categoryDao.findByUserId(userId)); // 사이드바 카테고리
			// model.addAttribute("postList", postDao.findByUserId(userId)); // 블로그 전체게시글
			// model.addAttribute("visit", visitDao.findByVisitCount(userId));
		}
		return "/post/detailForm";
	}

	// 게시글 삭제 응답
	@DeleteMapping("/api/post/delete/{postId}")
	public @ResponseBody ResponseDto<?> delete(@PathVariable Integer postId) {

		postRepository.delete(postId);
		return new ResponseDto<>(1, "게시글 삭제 성공", null);
	}

	// 게시글 좋아요 응답
	// @PostMapping("/s/api/post/love/{postId}")
	// public @ResponseBody ResponseDto<?> insertLove(@PathVariable Integer postId) {

	// 	User principal = (User) session.getAttribute("principal");

	// 	Love love = new Love(principal.getUserId(), postId);

	// 	postService.좋아요(love);
	// 	return new ResponseDto<>(1, "좋아요 성공", love);
	// }

	// 게시글 싫어요 응답
	// @DeleteMapping("/s/api/post/love/{postId}/{loveId}")
	// public @ResponseBody ResponseDto<?> deleteLove(@PathVariable Integer postId, @PathVariable Integer loveId) {
	// 	postService.좋아요취소(loveId);

	// 	return new ResponseDto<>(1, "좋아요 취소 성공", null);
	// }

}