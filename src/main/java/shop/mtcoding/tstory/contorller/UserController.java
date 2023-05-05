package shop.mtcoding.tstory.contorller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpSession session;
    private final UserRepository userRepository;
    private final UserService userService;

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
        return "/user/loginForm";
    }

    // 로그인 응답
    @PostMapping("/login")
    public String login(LoginDto loginDto) {
        User userPS = userRepository.login(loginDto);
       
        if (userPS != null) {
            session.setAttribute("principal", userPS);
            return "redirect:/";
        } else {
            return "redirect:/loginForm";
        }
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
    @GetMapping("/passwordCheckForm")
    public String passwordCheckForm() {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/loginForm";
        }
        return "/user/passwordCheckForm";
    }

    // 비밀번호 확인 응답
    @PostMapping("/user/checkPassword")
    public @ResponseBody ResponseDto<?> passwordCheck(@RequestBody PasswordCheckDto passwordCheckDto) {
        User principal = (User) session.getAttribute("principal");
        User userPS = userRepository.findByPasswordAndUserId(passwordCheckDto.getPassword(), principal.getUserId());
        if (userPS == null) {
            return new ResponseDto<>(-1, "실패", null);
        }
        return new ResponseDto<>(1, "성공", null);
    }

    // 비밀번호 수정 페이지
    @GetMapping("/user/passwordUpdateForm")
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
    @GetMapping("/user/updateForm")
    public String updateForm(Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/loginForm";
        }
        model.addAttribute("user", userRepository.findById(principal.getUserId()));
        return "/user/updateForm";
    }

    // 계정 수정 응답
    @PostMapping("/user/update")
    public @ResponseBody ResponseDto<?> update(@RequestBody UserUpdateDto userUpdateDto) {
        User principal = (User)session.getAttribute("principal");
        userRepository.updateById(userUpdateDto.getPasswordUpdate(),userUpdateDto.getEmail(), principal.getUserId());
        return new ResponseDto<>(1, "성공", null);
    }

    // 프로필 수정 페이지
    @GetMapping("/user/profileUpdateForm")
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
    @PostMapping("/user/profileUpdate")
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
    @DeleteMapping("/user/leave")
    public @ResponseBody ResponseDto<?> leave() {
        User principal = (User) session.getAttribute("principal");
            userRepository.delete(principal.getUserId());
            session.invalidate();
            return new ResponseDto<>(1, "성공", null);
        }

    // 비밀번호 수정 응답
    @PostMapping("/user/updatePassword")
    public @ResponseBody ResponseDto<?> updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto) {
        User principal = (User) session.getAttribute("principal");
        userRepository.updateByPassword(updatePasswordDto.getPasswordUpdate(), principal.getUserId());

        return new ResponseDto<>(1, "성공", null);
    }

    // 닉네임 수정 응답
    @PostMapping("/user/updateNickname")
    public @ResponseBody ResponseDto<?> updateNickname(@RequestBody UpdateNicknameDto updateNicknameDto) {
    
        User principal = (User) session.getAttribute("principal");
        userRepository.updateByNickname(updateNicknameDto.getNicknameUpdate(), principal.getUserId());

        return new ResponseDto<>(1, "성공", null);
    }


}