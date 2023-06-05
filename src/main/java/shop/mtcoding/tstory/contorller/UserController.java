package shop.mtcoding.tstory.contorller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.tstory.dto.ResponseDto;
import shop.mtcoding.tstory.dto.user.JoinDto;
import shop.mtcoding.tstory.dto.user.LoginDto;
import shop.mtcoding.tstory.dto.user.PasswordCheckDto;
import shop.mtcoding.tstory.dto.user.UpdateNicknameDto;
import shop.mtcoding.tstory.dto.user.UpdatePasswordDto;
import shop.mtcoding.tstory.dto.user.UpdateProfileReqDto;
import shop.mtcoding.tstory.dto.user.UserUpdateDto;
import shop.mtcoding.tstory.model.user.User;
import shop.mtcoding.tstory.model.user.UserRepository;
import shop.mtcoding.tstory.service.UserService;
import shop.mtcoding.tstory.util.SHA256;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession session;
    private final UserRepository userRepository;
    private final UserService userService;
    private final SHA256 sha256;

    // 회원가입 페이지
    @GetMapping("/joinForm")
    public String joinForm() {
        return "/user/joinForm";
    }

    // 회원가입 응답
    @PostMapping("/join")
    public @ResponseBody ResponseDto<?> join(@RequestBody JoinDto joinDto) {
        userService.회원가입(joinDto);
        return new ResponseDto<>(1, "회원가입성공", null);
    }

    // 로그인 페이지
    @GetMapping("/loginForm")
    public String loginForm() {
        User principal = (User) session.getAttribute("principal");
        if (principal != null) {
            return "redirect:/";
        }
        return "/user/loginForm";
    }

     // 로그인 응답
     @PostMapping("/login")
     public @ResponseBody ResponseDto<?> login(@RequestBody LoginDto loginDto) {
         System.out.println("디버그 :" );
        User userIdPS = userRepository.findByUsername(loginDto.getUsername());
        if (userIdPS == null) {
            return new ResponseDto<>(-1, "아이디 혹은 비밀번호를 잘못 입력하셨습니다.", null);
        }
        
        String encPassword = sha256.encrypt(loginDto.getPassword());
        
        User usersPS = userRepository.findByUsernameAndenPassword(encPassword, loginDto.getUsername());
        if (usersPS == null) {
            return new ResponseDto<>(-1, "아이디 혹은 비밀번호를 잘못 입력하셨습니다.", null);
        }
        if (userIdPS.getRole().equals("admin")) {
            userService.로그인(loginDto);
            return new ResponseDto<>(2, "관리자님 환영합니다.", null);
        }
        userService.로그인(loginDto);
        return new ResponseDto(1, "로그인 되셨습니다.", null);

     }

    // 로그아웃
    @GetMapping("/logout")
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
    @GetMapping("/api/passwordCheckForm")
    public String passwordCheckForm(Model model) {
        User principal = (User) session.getAttribute("principal");
        model.addAttribute("userImg", userRepository.findById(principal.getUserId()));
        //model.addAttribute("userImg", principal);
        if (principal == null) {
            return "redirect:/loginForm";
        }
        return "/user/passwordCheckForm";
    }

    // 비밀번호 확인 응답
    @PostMapping("/api/user/checkPassword")
    public @ResponseBody ResponseDto<?> passwordCheck(@RequestBody PasswordCheckDto passwordCheckDto) {
        User principal = (User) session.getAttribute("principal");
        User userPS = userRepository.findByPasswordAndUserId(passwordCheckDto.getPassword(), principal.getUserId());
        if (userPS == null) {
            return new ResponseDto<>(-1, "실패", null);
        }
        return new ResponseDto<>(1, "성공", null);
    }

    // 비밀번호 수정 페이지
    @GetMapping("/api/user/passwordUpdateForm")
    public String passwordUpdateForm() {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/loginForm";
        }
        return "/user/passwordUpdateForm";
    }

    // 이메일 응답 페이지
    // @GetMapping("/user/emailCheckForm")
    // public String emailCheckForm() {
    //     User principal = (User) session.getAttribute("principal");
    //     if (principal == null) {
    //         return "redirect:/user/loginForm";
    //     }
    //     return "/user/emailCheckForm";
    // }

    // 계정 수정 페이지
    @GetMapping("/api/user/updateForm")
    public String updateForm(Model model) {
        User principal = (User) session.getAttribute("principal");
        //model.addAttribute("userImg", userRepository.findById(principal.getUserId()));
        if (principal == null) {
            return "redirect:/loginForm";
        }
        model.addAttribute("userImg", userRepository.findById(principal.getUserId()));
        return "/user/updateForm";
    }

    // 계정 수정 응답
    @PostMapping("/api/user/update")
    public @ResponseBody ResponseDto<?> update(@RequestBody UserUpdateDto userUpdateDto) {
        User principal = (User)session.getAttribute("principal");
        userRepository.updateById(userUpdateDto.getPasswordUpdate(),userUpdateDto.getEmail(), principal.getUserId());
        return new ResponseDto<>(1, "성공", null);
    }

    // 프로필 수정 페이지
    @GetMapping("/api/user/profileUpdateForm")
    public String profileUpdateForm(Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/loginForm";
        }
        User userPS = userRepository.findById(principal.getUserId());
        model.addAttribute("user", userPS);
        model.addAttribute("userImg", userRepository.findById(principal.getUserId()));
        return "/user/profileUpdateForm";
    }

    // 프로필 수정 응답
    @PostMapping("/api/user/profileUpdate")
    public @ResponseBody ResponseDto<?> updateProfile(@RequestPart("file") MultipartFile file,
            @RequestPart("updateProfileDto") UpdateProfileReqDto updateProfileDto) throws Exception {
        int pos = file.getOriginalFilename().lastIndexOf(".");
        String extension = file.getOriginalFilename().substring(pos + 1);
        String filePath = "src/main/resources/static/img/";
        String imgSaveName = UUID.randomUUID().toString();
        String imgName = imgSaveName + "." + extension;
        

        File makeFileFolder = new File(filePath);
        if (!makeFileFolder.exists()) {
            if (!makeFileFolder.mkdir()) {
                throw new Exception("File.mkdir():Fail.");
            }
        }
        File dest = new File(filePath, imgName);
        try {
            Files.copy(file.getInputStream(), dest.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("사진저장");
        }
        
        
        updateProfileDto.setProfileImg(imgName);
        userService.프로필이미지변경하기(updateProfileDto.getProfileImg());

        return new ResponseDto<>(1, "업로드 성공", imgName);
    }

    // 회원 탈퇴 응답
    @DeleteMapping("/api/user/leave")
    public @ResponseBody ResponseDto<?> leave() {
        User principal = (User) session.getAttribute("principal");
            userRepository.delete(principal.getUserId());
            session.invalidate();
            return new ResponseDto<>(1, "성공", null);
        }

    // 비밀번호 수정 응답
    @PostMapping("/api/user/updatePassword")
    public @ResponseBody ResponseDto<?> updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto) {
        User principal = (User) session.getAttribute("principal");
        userRepository.updateByPassword(updatePasswordDto.getPasswordUpdate(), principal.getUserId());

        return new ResponseDto<>(1, "성공", null);
    }

    // 닉네임 수정 응답
    @PostMapping("/api/user/updateNickname")
    public @ResponseBody ResponseDto<?> updateNickname(@RequestBody UpdateNicknameDto updateNicknameDto) {
    
        User principal = (User) session.getAttribute("principal");
        userRepository.updateByNickname(updateNicknameDto.getNicknameUpdate(), principal.getUserId());

        return new ResponseDto<>(1, "성공", null);
    }


}