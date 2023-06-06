<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="../layout/main-header.jsp"%>
<div class="container">
    <div class="my_auth_box">
        <div class="my_auth_form_box">
            <div class="my_auth_form_box_title">JSotry</div>
            
                <input
                    class="my_auth_form_box_input"
                    type="text"
                    name="username"
                    id="username"
                    placeholder="유저네임"
                    value="ssar"
                />
                <input
                    class="my_auth_form_box_input"
                    type="password"
                    name="password"
                    id="password"
                    placeholder="비밀번호"
                    value="1234"
                />
                <button class="my_secondary_btn" onclick="login()" >로그인</button>
            
            <div class="my_auth_form_box_link">
                <div><a href="/joinForm">회원가입</a></div>
                <div><a href="/user/passwordResetForm">비밀번호 찾기</a></div>
            </div>
        </div>
    </div>
    <br />
</div>

<script>
function login() {
        let data = {
            username: $("#username").val(),
            password: $("#password").val()
        };

        $.ajax("/login", {
            type: "POST",
            dataType: "json",
            data: JSON.stringify(data),
            headers: {
                "Content-Type": "application/json",
            },
        }).done((res) => {
            if (res.code == 1) {
                alert("성공");
                location.href = "/";
            } 
            
            else if (res.code == 2) {
                alert(res.msg);
                location.href = "/admin/memberManageForm";
            }

            else {
                alert(res.msg);
                location.href = "";
            }
        });
    }

</script>
<%@ include file="../layout/footer.jsp"%>
