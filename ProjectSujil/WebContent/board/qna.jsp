<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dto.AdminDTO"%>
<%@ page import="com.dto.QnaDTO"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--메세지 파싱/ 관리자 정보 파싱  -->
	<%
		String mesg = (String) request.getAttribute("mesg");
	AdminDTO login = (AdminDTO) session.getAttribute("admin");
	if (login != null) {
	%>
	<h1>login ok</h1>
	<button>
		<a href="AdminLogoutServlet">로그아웃</a>
	</button>
	<%
		} if( mesg != null){
	%>
	<script type="text/javascript">
	alert('<%=mesg%>');
	</script>
	
	<%}
		session.removeAttribute("mesg");
	%>
	<div id="qnaDiv">
	<h1>QnA</h1>
	<!--게시판 작성 인풋 부분 -->
	<form action="QnaWriteServlet" method="get">
		<div id="boardHead" class="content">
			<span id="titleText" class="titleSpan">제목:</span>
			<input type="text" name="Qtitle" id="title" placeholder="10글자 이상 입력해주세요.">
			<span id="titleHide" class="titleSpan">비밀글:</span>
			<input type="checkbox" name="Qhide" id="hide">
			<br>
			<span id="titleName" class="titleSpan">작성자:</span>
			<input type="text" name="Qusername" id="username"><br> 
			<span id="titleEmail" class="titleSpan">이메일:</span>
			<input type="text" name="Qemail" id="email"><br> 
			<span id="titlePasswd" class="titleSpan">비밀번호:</span>
			<input type="password" name="Qpasswd4" id="passwd" placeholder="4자리" maxlength="4">

		</div>
		<hr>
		<textarea class="content" name="Qcontent" id="content" cols="30"></textarea>
		<br>
		<button id="submit">작성하기</button>
	</form>
	<br>
	<br>
	<!-- 게시글 리스트 파싱 -->
	<%
		List<QnaDTO> list = (List<QnaDTO>) request.getAttribute("list");
	for (int i = 0; i < list.size(); i++) {
		QnaDTO qDTO = list.get(i);
		int num = qDTO.getQnum();
		String title = qDTO.getQtitle();
		String username = qDTO.getQusername();
		String content = qDTO.getQcontent();
		String reply = qDTO.getQreply();
		String hide = qDTO.getQhide();
		String passwd = qDTO.getQpasswd4();
	%>
		
		<!-- 관리자로그인 후  페이지 -->
		<%
			if (login != null) {
		%>
		<div id="qnaRead">
			<span id="QnaReadTitle" <%=num%>>
				<span><%=num%></span>.</span>
				<strong><%=title%></strong> <br>
			<span id="QnaReadUserName"><%=username%></span>
					<form id="userFixForm<%=num %>" action="QnaPasswdCheck" method="post">			
						<%--히든으로 보낼 경우 html 에서 표시되기 때문에 select 로 passwd를 꺼내와서 작성
						 <input type="hidden" value=<%=passwd%> name="passwdCheck"> --%>
						<input type="hidden" name="passwdUser" value=<%=num%>> 
						<input type="password" name="passwdCheck2" maxlength="4">
						<button class="userFix" data =<%=num %>>수정</button>
						<button class="userDel" data =<%=num %>>삭제</button>
					</form>
			<hr>
			<div id="qnaContent"><%=content%></div>
			<hr>
			<%
				if (reply != null) {
			%>
			<span id="qnaReplyContent">ㄴ<%=reply%></span>

			<%
				} else {
			%>
			<span>ㄴ답변을 준비중 입니다.</span>
			<%
				}
			%>
		<br>
		<form action="QnaReplyUpdate">
			<input type="hidden" name="qnaReplyNum" value="<%=num%>">
			<textarea name="replyUpdateArea"></textarea><br>
			<input type="submit" value="등록">
		</form>
		<hr>
		<% } else { %>
			<!-- 비로그인 -->
				<%	
				if (hide.equals("off")) {
				%>	
				<div id="qnaRead">
					<span id="QnaReadTitle<%=num%>">
						<span><%=num%></span>. </span><strong><%=title%></strong>
					</span> <br>
					<span id="QnaReadUserName"><%=username%></span>
					<form id="userFixForm<%=num %>" action="QnaPasswdCheck" method="post">
						
						<%--히든으로 보낼 경우 html 에서 표시되기 때문에 select 로 passwd를 꺼내와서 작성
						 <input type="hidden" value=<%=passwd%> name="passwdCheck"> --%>
						<input type="hidden" name="passwdUser" value=<%=num%>> 
						<input type="password" name="passwdCheck2" maxlength="4">
						<button class="userFix" data =<%=num %>>수정</button>
						<button class="userDel" data =<%=num %>>삭제</button>
					</form>
					<hr>
					<div id="qnaContent"><%=content%></div>
					<hr>
					<!-- 답변  표시 -->
					<%
						if (reply != null) {
					%>
					<span id="qnaReplyContent">ㄴ<%=reply%></span>
					<hr>
					<%
						} else {
					%>
					<span>ㄴ답변을 준비중 입니다.</span>
							<hr>
					<%
						}
					%>
			<% } else {	%>
			<div id="qnaRead">
				<span id="QnaReadTitle" <%=num%>><span><%=num%></span>. </span><strong>
					비밀글 입니다. </strong></span><br> <br>
				<span id="QnaReadUserName"><%=username%></span>
						<form id="userFixForm<%=num %>" action="QnaPasswdCheck" method="post">
						
						<%--히든으로 보낼 경우 html 에서 표시되기 때문에 select 로 passwd를 꺼내와서 작성
						 <input type="hidden" value=<%=passwd%> name="passwdCheck"> --%>
						<input type="hidden" name="passwdUser" value=<%=num%>> 
						<input type="password" name="passwdCheck2" maxlength="4">
						<button class="userFix" data =<%=num %>>수정</button>
						<button class="userDel" data =<%=num %>>삭제</button>
					</form>	
					<hr>
				<div id="qnaContent">비밀</div>
				<hr>
		<%
						if (reply != null) {
					%>
					<span id="qnaReplyContent">ㄴ<%=reply%></span>
					<hr>
					<%
						} else {
					%>
					<span>ㄴ답변을 준비중 입니다.</span>
							<hr>
					<%
						}
					%>	
				<%
					}
				%>
				<%
					}
				%>

				<%
					}
				%>
			</div>
	</div>
		<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="js/qna.js"></script>
</body>
</html>