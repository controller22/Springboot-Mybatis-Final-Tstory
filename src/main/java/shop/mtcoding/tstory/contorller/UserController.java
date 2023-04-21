package shop.mtcoding.tstory.contorller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.tstory.dto.ResponseDto;
import shop.mtcoding.tstory.dto.user.JoinDto;
import shop.mtcoding.tstory.model.user.User;
import shop.mtcoding.tstory.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession session;
    // private final UserDao userDao;
    private final UserService userService;

    // 회원가입 페이지
    @GetMapping("/joinForm")
    public String joinForm() {
        return "/user/joinForm";
    }

    // 회원가입 응답
    @PostMapping("/join")
    public @ResponseBody ResponseDto<?> join(@RequestBody JoinDto joinDto) {
        System.out.println("디버깅 : "+joinDto.getUsername());
        System.out.println("디버깅 : "+joinDto.getPassword());
        System.out.println("디버깅 : "+joinDto.getNickname());
        System.out.println("디버깅 : "+joinDto.getEmail());
        userService.회원가입(joinDto);
        return new ResponseDto<>(1, "회원가입성공", null);
    }

    // 로그인 페이지
    @GetMapping("/loginForm")
    public String loginForm() {
        return "/user/loginForm";
    }

    // 로그인 응답
    // @PostMapping("/user/login")
    // public String login(LoginDto loginDto) {
    //     User userPS = userDao.login(loginDto);
    //     if (userPS != null) {
    //         session.setAttribute("principal", userPS);
    //         return "redirect:/";
    //     } else {
    //         return "redirect:/user/loginForm";
    //     }
    // }

    // 로그아웃
    @GetMapping("/user/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    // 비밀번호 초기화 페이지
    @GetMapping("/user/passwordResetForm")
    public String passwordResetForm() {
        return "/user/passwordResetForm";
    }

    // 비밀번호 확인 페이지
    @GetMapping("/user/passwordCheckForm")
    public String passwordCheckForm() {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/user/loginForm";
        }
        return "/user/passwordCheckForm";
    }

    // 비밀번호 확인 응답
    // @PostMapping("/user/checkPassword")
    // public @ResponseBody CMRespDto<?> passwordCheck(@RequestBody PasswordCheckDto passwordCheckDto) {
    //     System.out.println("디버그: password: " + passwordCheckDto.getPassword());
    //     System.out.println("디버그: userId: " + passwordCheckDto.getUserId());
    //     User principal = (User) session.getAttribute("principal");
    //     User userPS = userDao.findByPasswordAndUserId(passwordCheckDto.getPassword(), principal.getUserId());
    //     if (userPS == null) {

    //         return new CMRespDto<>(-1, "실패", null);
    //     }
    //     return new CMRespDto<>(1, "성공", null);
    // }

    // 비밀번호 수정 페이지
    @GetMapping("/user/passwordUpdateForm")
    public String passwordUpdateForm() {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/user/loginForm";
        }
        return "/user/passwordUpdateForm";
    }

    // 이메일 응답 페이지
    @GetMapping("/user/emailCheckForm")
    public String emailCheckForm() {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/user/loginForm";
        }
        return "/user/emailCheckForm";
    }

    // 계정 수정 페이지
    @GetMapping("/user/updateForm")
    public String updateForm(Model model) {
        // User principal = (User) session.getAttribute("principal");
        // if (principal == null) {
        //     return "redirect:/user/loginForm";
        // }
        // model.addAttribute("user", userDao.findById(principal.getUserId()));
        return "/user/updateForm";
    }

    // 계정 수정 응답
    // @PostMapping("/user/update")
    // public String update(UserUpdateDto userUpdateDto) {
    //     userDao.updateById(userUpdateDto);
    //     return "redirect:/";
    // }

    // 프로필 수정 페이지
    @GetMapping("/user/profileUpdateForm")
    public String profileUpdateForm(Model model) {
        // User principal = (User) session.getAttribute("principal");
        // if (principal == null) {
        //     return "redirect:/user/loginForm";
        // }
        // User userPS = userDao.findById(principal.getUserId());
        // model.addAttribute("user", userPS);
        return "/user/profileUpdateForm";
    }

    // 회원 탈퇴 페이지
    @GetMapping("/user/leaveCheckForm")
    public String leaveCheckForm() {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/user/loginForm";
        }
        return "/user/leaveCheckForm";
    }

    // 회원 탈퇴 응답
    // @DeleteMapping("/user/leave")
    // public @ResponseBody CMRespDto<?> leave(@RequestBody LeaveDto leaveDto) {
    //     User principal = (User) session.getAttribute("principal");
    //     System.out.println("디버그: password: " + leaveDto.getPassword());
    //     System.out.println("디버그: userId: " + leaveDto.getUserId());
    //     User userPS = userDao.findByPasswordAndUserId(leaveDto.getPassword(), principal.getUserId());
    //     if (userPS != null) {
    //         session.invalidate();
    //         userDao.leave(principal.getUserId());
    //         return new CMRespDto<>(1, "성공", null);
    //     }
    //     return new CMRespDto<>(-1, "실패", null);
    // }

    // 비밀번호 수정 응답
    // @PostMapping("/user/updatePassword")
    // public @ResponseBody CMRespDto<?> updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto) {
    //     User principal = (User) session.getAttribute("principal");
    //     User userPS = userDao.findByPasswordAndUserId(updatePasswordDto.getPassword(), principal.getUserId());
    //     if (userPS == null) {
    //         return new CMRespDto<>(-1, "실패", null);
    //     }
    //     userDao.updateByPassword(updatePasswordDto.getPasswordUpdate(), principal.getUserId());

    //     return new CMRespDto<>(1, "성공", null);
    // }

    // 닉네임 수정 응답
    // @PostMapping("/user/updateNickname")
    // public @ResponseBody CMRespDto<?> updateNickname(@RequestBody UpdateNicknameDto updateNicknameDto) {
    //     User principal = (User) session.getAttribute("principal");
    //     userDao.updateByNickname(updateNicknameDto.getNicknameUpdate(), principal.getUserId());
    //     return new CMRespDto<>(1, "성공", null);
    // }

}