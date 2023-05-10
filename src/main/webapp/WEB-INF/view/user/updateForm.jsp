<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="../layout/main-header.jsp"%>

<input type="hidden" id="userId" value="${user.userId}" />

<div class="container"  >
    <div class="my_auth_box">
        <div class="my_auth_form_box">
            <!-- 프로필 이미지 -->
            <div class="d-flex justify-content-center"></div>

            <!-- 계정정보 -->
            <div>
                <input type="hidden" name="userId" id="userId" value="${user.userId}" />

                <div class="my_auth_form_box_info">
                    <i class="fa-solid fa-user"></i> 계정정보
                </div>

                <div style="display: flex">
                    <div class="my_auth_form_box_info_detail"  style="width: 100px; margin:15px;">아이디</div>
                    <input
                        id="username"
                        class="my_auth_form_box_input"
                        placeholder="${user.username}"
                        maxlength="20"
                        required
                        readonly
                    />
                </div>
                <div style="display: flex">
                    <div class="my_auth_form_box_info_detail" style="width: 100px; margin:15px;">이메일</div>
                    <input
                        id="email"
                        name="email"
                        class="my_auth_form_box_input"
                        type="email"
                        value="${user.email}"
                        maxlength="60"
                        required
                    />
                </div>
                
            </div>

            
           

            <!-- 보안 -->
            <div>
                

                <input type="hidden" value="${principal.userId}" id="userId" />


            <div style="display: flex">
                <div class="my_auth_form_box_info_security_detail" style="width: 200px; margin:15px;" >
                    변경할 비밀번호
                </div>
                <input
                    oninput="validPassword()"
                    id="passwordUpdate"
                    class="my_auth_form_box_input"
                    type="password"
                    placeholder="변경할 비밀번호를 입력해주세요."
                    maxlength="20"
                    required
                /><i class="fa fa-eye fa-lg" style="line-height: 60px;"></i>
            </div>
            <span
                class="passwordValid"
                style="padding-left: 130px; color: red; display: none"
            ></span>

            <div style="display: flex">
                <div class="my_auth_form_box_info_security_detail" style="width: 200px; margin:15px;">
                    비밀번호 확인
                </div>

                <input
                    oninput="validPasswordSame()"
                    id="passwordUpdateSame"
                    class="my_auth_form_box_input"
                    type="password"
                    placeholder="비밀번호를 확인해주세요."
                    maxlength="20"
                    required
                /><i class="fa fa-eye fa-lg" style="line-height: 60px;"></i>
            </div>
            <span
                class="passwordSameValid"
                style="padding-left: 130px; color: red; display: none"
            ></span>
            <div style="text-align: right">
                <button
                    onclick="update()"
                    class="btn btn-outline-primary"
                >
                    회원정보 수정
                </button>
            </div>
        
            </div>
            <hr />
            

             <!-- 프로필 -->

            <div>
                <div class="my_auth_form_box_security">
                    <i class="fa fa-info-circle" aria-hidden="true"></i>        프로필
                </div>

                <div>
                    <div style="text-align: right">
                        <a href="/api/user/profileUpdateForm">
                            <button
                                type="submit"
                                class="btn btn-outline-primary"
                            >
                                프로필 변경
                            </button></a
                        >
                    </div>
                </div>
            </div>
            <hr />
            <!-- 위험 -->
            <div>
                <div>
                    <i
                        class="fa-solid fa-triangle-exclamation fa-lg"
                        style="color: red"
                    >
                        위험</i
                    >
                </div>
               
                    <div style="text-align: right">
                         <button id="btnDelete" onclick="leave()" class="btn btn-outline-danger">
                            회원탈퇴
                        </button>
                    </div></a
                >
            </div>
        </div>
    </div>
</div>


<script>

$("#btnUpdate").click(() => {
	leave();
});

function leave() {
  if(confirm("작성하신 게시글들이 모두 삭제됩니다. 정말 탈퇴하시겠습니까?")==true){ 
	let data = {
		userId: $("#userId").val()
	};

	$.ajax("/api/user/leave", {
		type: "DELETE",
		dataType: "json", // 응답 데이터
		data: JSON.stringify(data), // http body에 들고갈 요청 데이터
		headers: { // http header에 들고갈 요청 데이터
			"Content-Type": "application/json; charset=utf-8"
		}
	}).done((res) => {
		if (res.code == 1) {
            alert("회원탈퇴가 완료되었습니다.");
            location.href = "/";
		} else {
            alert("비밀번호가 맞지 않습니다.");
        }
	});}
}


    function update() {
        if (validPassword()) {
            alert("변경할 비밀번호 정보를 다시 확인해주세요.");
            return;
        }

        if (validPasswordSame()) {
            alert("변경할 비밀번호가 일치하지 않습니다.");
            return;
        }

        let data = {
            passwordUpdate: $("#passwordUpdate").val(),
            email: $("#email").val()
        };

        $.ajax("/api/user/update", {
            type: "POST",
            dataType: "json",
            data: JSON.stringify(data),
            headers: {
                "Content-Type": "application/json; charset=utf-8",
            },
        }).done((res) => {
            if (res.code == 1) {
                alert("현재 회원정보가 변경되었습니다.");
                location.href = "/";
            } else {
                alert("회원정보를 다시 확인해주세요.");
                return false;
            }
        });
    }

    function validPassword() {
        let passwordUpdate = $("#passwordUpdate").val();
        let passwordUpdateSame = $("#passwordUpdateSame").val();

        var spaceRule = /\s/g;
        var korRule = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

        if (korRule.test(passwordUpdate)) {
            $(".passwordValid").css("display", "inline-block");
            $(".passwordValid").text(
                "숫자, 영문 대소문자, 특수문자 중 두가지 이상으로 조합해 주십시오."
            );
            return true;
        }

        if (spaceRule.test(passwordUpdate)) {
            $(".passwordValid").css("display", "inline-block");
            $(".passwordValid").text("공백을 제거해주세요");
            return true;
        }

        if (passwordUpdate.length < 1) {
            $(".passwordValid").css("display", "inline-block");
            $(".passwordValid").text("변경할 비밀번호는 필수 정보입니다.");
            return true;
        }


        if (passwordUpdate.length < 8 || passwordUpdate.length > 30) {
            $(".passwordValid").css("display", "inline-block");
            $(".passwordValid").text(
                "변경할 비밀번호는 8자~30자 내외로 입력해주세요."
            );
            return true;
        } else {
            $(".passwordValid").css("display", "none");
            return false;
        }
    }

    function validPasswordSame() {
        let passwordUpdate = $("#passwordUpdate").val();
        let passwordUpdateSame = $("#passwordUpdateSame").val();

        if (passwordUpdate != passwordUpdateSame) {
            $(".passwordSameValid").css("display", "inline-block");
            $(".passwordSameValid").text(
                "변경할 비밀번호가 일치하지 않습니다."
            );
            return true;
        }
        if (passwordUpdateSame.length < 1) {
            $(".passwordSameValid").css("display", "inline-block");
            $(".passwordSameValid").text(
                "변경할 비밀번호 재확인은 필수정보입니다."
            );
            return true;
        } else {
            $(".passwordSameValid").css("display", "none");
            return false;
        }
    }

     // 비밀번호 미리보기  =====================================
    $(document).ready(function () {
        $(".my_auth_box i").on("click", function () {
            $("input").toggleClass("active");
            if ($("input").hasClass("active")) {
                $(this)
                    .attr("class", "fa fa-eye-slash fa-lg")
                    .prev("input")
                    .attr("type", "text");
            } else {
                $(this)
                    .attr("class", "fa fa-eye fa-lg")
                    .prev("input")
                    .attr("type", "password");
            }
        });
    });
</script>

<%@ include file="../layout/footer.jsp"%>