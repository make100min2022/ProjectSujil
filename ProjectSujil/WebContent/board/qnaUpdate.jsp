<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.dto.QnaDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
QnaDTO qDTO = (QnaDTO) request.getAttribute("qDTO");
int qnum = qDTO.getQnum();
String qUsername = qDTO.getQusername();
String qContent = qDTO.getQcontent();
String qEmail= qDTO.getQemail();
String qTitle = qDTO.getQtitle();

%>

<form action="QnaUpdateWriteServlet" method="get">
        <div id="boardHead" class="content">
        	<input type="hidden" value=<%=qnum %> name="Qnum">
            <span id="titleText" class="titleSpan">제목:</span><input type="text" name="Qtitle" id="title" value=<%=qTitle %>><br>
            <span id="titleName" class="titleSpan">작성자:</span><input type="text" name="Qusername" id="username" value =<%=qUsername %>><br>
            <span id="titleEmail" class="titleSpan">이메일:</span><input type="text" name="Qemail" id="email" value = <%=qEmail %>><br>
            <span id="titlePasswd" class="titleSpan">비밀번호:</span><input type="password" name="Qpasswd4" id="passwd" placeholder ="변경시에만 입력해주세요." maxlength="4">
     		<span id="titleHide" class="titleSpan">비밀글:</span><input type="checkbox" name ="Qhide" id="hide">
   	     </div>
        <hr>
        <textarea class="content" name="Qcontent" id="content" cols="30" ><%=qContent %></textarea><br>
        <button id="submit">작성하기</button>
        <script type="text/javascript" src = "js/jquery-3.3.1.js"></script>
        <script type="text/javascript" src = "js/qna.js"></script>
    </form>
</body>
</html>