package shop.mtcoding.tstory.handler;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.tstory.dto.ResponseDto;
import shop.mtcoding.tstory.model.user.User;

public class LoginIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println(request.getRequestURI());

        String uri = request.getRequestURI();

        HttpSession session = request.getSession();
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            if (uri.contains("api")) {
                // System.out.println("디버그 : API 가 주소에 있음");

                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = response.getWriter();
                ResponseDto<?> responseDto = new ResponseDto<>(-1, "로그인을 진행해주세요", null);
                ObjectMapper om = new ObjectMapper();
                String json = om.writeValueAsString(responseDto);
                out.println(json);
            } else {
                // System.out.println("디버그 : API 가 주소에 없음");
                response.sendRedirect("/loginForm");
            }
            return false;
        }
        return true;
    }
}