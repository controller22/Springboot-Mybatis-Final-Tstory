<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="../layout/post-header.jsp"%>

<div class="container">
    <div class="my_post_detail_title">
        <h2>${post.postTitle}</h2>
    </div>
    <br>
    <div style="display: flex;" >

        
        <c:choose>
    <c:when test="${principal.userId==post.userId}">
        <div style="line-height: 40px;">
        작성자:<a href="/post/listForm/${post.userId}"> ${user.nickname}</a>&nbsp;&nbsp;
                최근 수정일: ${post.updatedAt}
        </div>
        </c:when>
        <c:otherwise>
        <div  >
        작성자:<a href="/post/listForm/${post.userId}"> ${user.nickname}</a>&nbsp;&nbsp;
                최근 수정일: ${post.updatedAt}
        </div>

        </c:otherwise>
        </c:choose>
        <div style="display: inline-flex; padding-left: 30px;">
        <c:if test="${principal.userId==post.userId}">
            <a
                class="btn btn-outline-warning"
                href="/api/post/updateForm/${post.postId}"
                style="height:38px;width: 60px;"
                >수정</a
            >
            <form>
                <button id="btnDelete" onclick="remove()" class="btn btn-outline-danger">
                    삭제
                </button>
            </form>
        </c:if>
    </div>
    </div>
    <hr><br>
   

    <div class="my_post_detail_content">${post.postContent}</div>

    <div class="my_post_info_box d-flex" style="margin-top: 50px;">

        <input id="postId" type="hidden" value="${post.postId}" />
        <input id="userId" type="hidden" value="${post.userId}" />
      
    <br />
</div>
<script>
	
    
function remove() {
    let postId = $("#postId").val();

    if(confirm("작성하신 게시글이 삭제됩니다. 정말 삭제하시겠습니까?")==true){ 
      $.ajax({
        type: "Delete",
        url: `/api/post/delete/${postId}`,
		dataType: "json", // 응답 데이터
		ContentType: "application/json",
	}).done((res) => {
		if (res.code == 1) {
            alert("게시글삭제가 완료되었습니다.");
            location.href = "/";
		} else {
            alert("경로가 올바르지 않습니다.");
        }
	});}
}




</script>

<script src="/js/post/detail.js"></script>
<%@ include file="../layout/footer.jsp"%>