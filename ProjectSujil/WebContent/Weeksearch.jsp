<%@page import="com.dto.WeekDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dto.JusoDTO" %>
<%@ page import="com.dto.DayDTO" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css"/>  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
  
<!--<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script> 
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 <script type="text/javascript" src="js/jquery-3.5.1.min.js"></script> -->
<script type="text/javascript">
	//조회 시 시도, 시군, 동 파라미터 담을 전역변수
	var globalParam1 = "";
	var globalParam2 = "";
	var globalParam3 = "";
	//var sigun = $("#sigun").val($("#sigun option:selected").val());
	//select 변경 시 주소 세팅
	$(document).ready(function(){
		//조회 전에는 "", 조회 후에는 시도, 시군, 동 값을 담는다.
		globalParam1 = [];
		globalParam2 = [];
		globalParam3 = [];
		
		//시도 변경에 따른 시군 세팅
		$("#sido").on("change", function() {
			var innerHtml="";
			$.ajax({
				type: "get",
				url: "sigunServlet",
				dataType: "text",
				//contentType: "application/x-www-form-urlencoded; charset=UTF-8",

				data:{
					sido : $("#sido option:selected").text()
				},
				success: function (data, status, xhr) {
					var data = decodeURIComponent(data);
											
						var data = data.replace("[","");
						data = data.replace("]","");
						console.log(data);
						
						var listdata = data.split(',');
					$("#sigun").children("option").remove();
					$("#sigun").append("<option value= ''>"+"선택하세요."+"</option>");
						for(var i in listdata){
							//innerHtml+=	"<option value= '"+listdata[i]+"'>"+listdata[i]+"</option>";
							$("#sigun").append("<option value= '"+listdata[i]+"'>"+listdata[i]+"</option>");
						//console.log(data);
					}
						//$("#sigun").append(innerHtml);	
				},
				error: function(xhr, status, e) {
					console.log(e);
				}
			});
			console.log( $("#sigun").val());
		});
		/* var sigun = $("#sigun").val();
		console.log("sigun:"+sigun); */
		
		//시군 변경에 따른 읍,면 동 변
		  $("#sigun").on("change", function() {
			
			$.ajax({
				type: "get",
				url: "dongServlet",
				dataType: "text",
				//contentType: "application/x-www-form-urlencoded; charset=UTF-8",

				data:{
					sigun : $("#sigun option:selected").val()
				},
				success: function (data, status, xhr) {
					//var data = decodeURIComponent(data);
											
						var data = data.replace("[","");
						data = data.replace("]","");
						console.log(data);
						
						
						var listdata = data.split(',');
						$("#dong").children("option").remove();
						$("#dong").append("<option value= ''>"+"선택하세요."+"</option>");
						
						
						for(var i in listdata){
						                                                                                                                              
						$("#dong").append("<option value= '"+listdata[i]+"'>"+listdata[i]+"</option>");
						//console.log(data);
					}
							
					
					
				},
				error: function(xhr, status, e) {
					console.log(e);
				}
			});
		});
		
			$("#searchBtn").on("click", function () {
				var sido = $("#sido option:selected").text;
				var sigun = $("#sigun option:selected").val().trim();
				var dong = $("#dong option:selected").val().trim();
				  if(sido == "선택하세요.") {
			            alert("검색할 지역을 모두 선택하세요.");
			            return false;
			         }
			         if($("#sigun option:selected").text() == "선택하세요.") {
			            alert("검색할 지역을 모두 선택하세요.");
			            return false;
			         }
			         if($("#dong option:selected").text() == "선택하세요.") {
			            alert("검색할 지역을 모두 선택하세요.");
			            return false;
			         }
				console.log(dong);
				var stdt = $("#stdt").datepicker().val();
				var eddt = $("#eddt").datepicker().val();
				 if($("#stdt").text() == " " || $("#eddt").text() == " "){
					 alert("검색할 날짜를 선택하세요.");
					return false;
				 }
				$("form").attr("action", "WeekDataServlet_T?sigun=" + sigun + "&dong=" + dong+"&stdt="+stdt+"&eddt="+eddt);
				//return false;
			});
		
		
		$.datepicker.setDefaults($.datepicker.regional['ko']);
		
		 $("#stdt").datepicker({
		        changeMonth:true,
		        changeYear:true,
		        yearRange:'c-5',
		        nextText:'다음 달',
		        prevText:'이전 달',
		        showMonthAfterYear: true,
		        dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],	        
		        dayNamesMin:["일","월","화","수","목","금","토"],
		        monthNameShort:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
		        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
		        dateFormat:'yy-mm-dd',
		        maxDate:-1,
		        
		        onClose:function(selectDate){
		            $("#eddt").datepicker("option","minDate",selectDate);
		        }
		 
		 
		    });
		 $("#eddt").datepicker({
		        changeMonth:true,
		        changeYear:true,
		        yearRange:'c-5',
		        nextText:'다음 달',
		        prevText:'이전 달',
		        showMonthAfterYear: true,
		        dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],	        
		        dayNamesMin:["일","월","화","수","목","금","토"],
		        monthNameShort:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
		        monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
		        dateFormat:'yy-mm-dd',
		        maxDate:-1,
		        
		        onClose:function(selectDate){
		            $("#stdt").datepicker("option","maxDate",selectDate);
		        }
		    });
	});
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String mesg =(String)request.getAttribute("mesg");
System.out.println(mesg);
if(mesg != null){
%>
<script type="text/javascript">
alert('<%=mesg%>' 
		);
</script>
<%
}
%>

<h2>주간 수질정보 조회</h2>
<div>
<p>조회 날짜 선택</p>
시작날짜 : <input type="text" id="stdt">
종료날짜 : <input type="text" id="eddt">
    <form name="searchForm" id="searchForm" method="post">
    <input type="hidden" name="pMENUID" value="147"/>
    <input type="hidden" name="ATTR_1" id="ATTR_1" value=""/>
    <input type="hidden" name="ATTR_2" id="ATTR_2" value=""/>
    <input type="hidden" name="ATTR_3" id="ATTR_3" value=""/>
        <div class="condition">
        
            <label>지역검색</label>
            <!-- 시도 -->
            <select name="sido" id="sido" class="W150" title="광역시도">
                <option value="">선택하세요.</option>
                <option value="서울특별시">서울특별시</option>
                    <option value="26">부산광역시</option>
                    <option value="27">대구광역시</option>
                    <option value="28">인천광역시</option>
                    <option value="29">광주광역시</option>
                    <option value="30">대전광역시</option>
                    <option value="31">울산광역시</option>
                    <option value="36">세종특별자치시</option>
                    <option value="41">경기도</option>
                    <option value="42">강원도</option>
                    <option value="43">충청북도</option>
                    <option value="44">충청남도</option>
                    <option value="45">전라북도</option>
                    <option value="46">전라남도</option>
                    <option value="47">경상북도</option>
                    <option value="48">경상남도</option>
                    <option value="50">제주특별자치도</option>
                    </select>
            <!-- 시군 -->
            <select name="sigun" id="sigun" class="W150" title="시군">
                <option value="">선택하세요.</option>
            </select>
            <!-- 읍면동 -->
            <select name="dong" id="dong" class="W150" title="읍면동">
                <option value="">선택하세요.</option>
            </select>
            <button id="searchBtn" title="조 회">조 회</button>
            <div id="result"></div>
		</div>
    </form>
</div>
<%
	List<WeekDTO> wList = (List<WeekDTO>)request.getAttribute("wList");
	if(wList != null){
%>
<h3>수질정보</h3>

<table border="1">
	<tr>
		<th>측정일</th>
		<th>일반 세균(CFU/mL)<img src="img/but.png" width="20pt" height="20pt"></th>
		<th>총 대장균(/100mL)<img src="img/but.png" width="20pt" height="20pt"></th>
		<th>대장균/분원성대장균(/100mL)<img src="img/but.png" width="20pt" height="20pt"></th>
		<th>암모니아성질소(mg/L)<img src="img/but.png" width="20pt" height="20pt"></th>
		<th>질산성질소(mg/L)<img src="img/but.png" width="20pt" height="20pt"></th>
		<th>증발잔류물(mg/L)<img src="img/but.png" width="20pt" height="20pt"></th>
	</tr>
<%
		for(int i = 0; i < wList.size(); i++){
			WeekDTO wDTO = wList.get(i);
			String cltdt = wDTO.getCltdt();
			String bacteria = wDTO.getAmmonia();
			String TotalColita = wDTO.getTotalColi();
			String Coli = wDTO.getColi();
			String Ammonia = wDTO.getAmmonia();
			String Nnitrogen = wDTO.getNnitrogen();
			String Evaresi = wDTO.getEvaresi();
%>
	<tr>
		<td><%= cltdt %></td>
		<td><%= bacteria %></td>
		<td><%= TotalColita %></td>
		<td><%= Coli %></td>
		<td><%= Ammonia %></td>
		<td><%= Nnitrogen %></td>
		<td><%= Evaresi %></td>
	</tr>
<%
		}//end for
%>
</table>
<%	
	}//end if
%>
</body>
</html>