package shop.mtcoding.tstory.contorller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.tstory.dto.ResponseDto;
import shop.mtcoding.tstory.model.post.PostRepository;
import shop.mtcoding.tstory.model.user.User;
import shop.mtcoding.tstory.model.user.UserRepository;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final HttpSession session;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // 사용자 관리 페이지
    @GetMapping("/admin/memberManageForm")
    public String userManageForm(Model model) {
        User principal = (User) session.getAttribute("principal");
        model.addAttribute("userInfo", userRepository.findById(principal.getUserId()));
        model.addAttribute("userList", userRepository.findAllMember());
        return "/admin/memberManageForm";
    }

    // 관리자 관리 페이지
    @GetMapping("/admin/adminManageForm")
    public String adminManageForm(Model model) {
        User principal = (User) session.getAttribute("principal");
        model.addAttribute("userInfo", userRepository.findById(principal.getUserId()));
        model.addAttribute("userList", userRepository.findAllAdmin());
        return "/admin/adminManageForm";
    }

    // 게시글 관리 페이지
    @GetMapping("/admin/postManageForm")
    public String postManageForm(Model model) {
        // User principal = (User) session.getAttribute("principal");
        // model.addAttribute("userInfo", userDao.findById(principal.getUserId()));
        model.addAttribute("postList", postRepository.findAllHistory());
        return "/admin/postManageForm";
    }

    // 사용자 삭제 기능
    @DeleteMapping("/admin/{userId}/delete")
    public @ResponseBody ResponseDto<?> userDelete(@PathVariable Integer userId) {
        System.out.println("디버그 : 컨도착");
        userRepository.delete(userId);
        return new ResponseDto<>(1, "사용자 삭제 성공", null);
    }
}
