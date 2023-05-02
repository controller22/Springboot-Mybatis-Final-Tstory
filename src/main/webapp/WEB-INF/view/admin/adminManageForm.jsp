<%@ include file="managementForm.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div style="display: flex; width: auto;text-align: center;padding : 50px;padding-left: 150px; "><table class="table table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>관리자 아이디</th>
				<th>가입일</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody><c:forEach var="user" items="${userList}">
				<tr>
					<td>${user.number}</td>
					<td>${user.username}</td>
					<td>${user.createdAt}</td>
					<input type="hidden" name="userId" id="userId" value="${user.userId}">
				 <td>
                        <a href="" class="btn btn-warning">수정</a>
                    </td>
                    <td>
                        <form>
                            <button onclick="remove()" class="btn btn-danger">삭제</button>
                        </form>
                    </td>
				</tr></c:forEach>
		</tbody>
	</table></div>
	

<script>
	function remove() {
	    let userId = $("#userId").val();

	    if(confirm("유저의 정보가 삭제됩니다. 정말 삭제하시겠습니까?")==true){ 
	      $.ajax({
			type: "Delete",
        	url: `/admin/${userId}/delete`,
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

</body>

</html>