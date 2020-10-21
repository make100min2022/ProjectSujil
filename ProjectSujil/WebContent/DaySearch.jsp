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
<!-- <link rel="stylesheet" href="js/jquery-ui-1.12.1/jquery-ui.min.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="js/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script src="js/jquery-ui-1.12.1/datepicker-ko.js"></script>
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script> -->
<script type="text/javascript">
	//조회 시 시도, 시군, 동 파라미터 담을 전역변수
	var globalParam1 = "";
	var globalParam2 = "";
	var globalParam3 = "";
	
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
				url: "SigunServlet",
				dataType: "text",
				//contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				data:{
					sido : $("#sido option:selected").val()
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
							$("#sigun").append("<option value= '" + listdata[i] + "'>" + listdata[i] + "</option>");
					}	
				},
				error: function(xhr, status, e) {
					console.log(e);
				}
			});
			console.log( $("#sigun").val());
		});

		//시군 변경에 따른 읍면동 세팅
		  $("#sigun").on("change", function() {
			
			$.ajax({
				type: "get",
				url: "DongServlet",
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
					$("#dong").append("<option value= ''>" + "선택하세요." + "</option>");
					
					for(var i in listdata){	                                                                                                                              
					$("#dong").append("<option value= '" + listdata[i] + "'>" + listdata[i] + "</option>");
					//console.log(data);
					}
				},
				error: function(xhr, status, e) {
					console.log(e);
				}
			});
		});
		
		//조회 버튼 클릭 시 시도, 시군, 동 값 유효성 검사 후 DayDataServlet으로 이동
		$("#searchBtn").on("click", function () {
			var sido = $("#sido option:selected").text();
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
			
			var stdt = $("#stdt").datepicker().val();
			var eddt = $("#eddt").datepicker().val();
			console.log(stdt);
			console.log(eddt);
			$("form").attr("action", "DayDataServlet?sigun=" + sigun + "&dong=" + dong
					+ "&stdt=" + stdt + "&eddt=" + eddt);				
		});
			
		//달력(datepicker) 기본 설정
		$.datepicker.setDefaults($.datepicker.regional['ko']);
		
		//시작일
		$("#stdt").datepicker({
	    	changeMonth: true, 
            changeYear: true,
            yearRange: 'c-5',
            nextText: '다음 달',
            prevText: '이전 달',
            showMonthAfterYear: true,
            dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            dateFormat: "yymmdd",
            maxDate: 0,                       // 선택할수있는 최소날짜, ( 0 : 오늘 이후 날짜 선택 불가)
            onClose: function(selectedDate) {    
                 //시작일(startDate) datepicker가 닫힐때
                 //종료일(endDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
                $("#eddt").datepicker("option", "minDate", selectedDate);
            }
	    });
	    //종료일
	    $("#eddt").datepicker({
	    	changeMonth: true,//월을 바꿀 수 있는 select box 표시
            changeYear: true,//년도를 바꿀 수 있는 select box 표시
            yearRange: 'c-5',//현재 년도를 기준, 년도 select box의 범위
            nextText: '다음 달',//next 아이콘의 툴팁
            prevText: '이전 달',//prev 아이콘의 툴팁
            showMonthAfterYear: true,// 월,년순의 셀렉트 박스를 년,월 순으로 바꿔줌
            dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],//표시할 요일 이름 설정
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],//요일의 한글 형식
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],//월의 한글 형식
            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],//표시할 월 이름 설정
            dateFormat: "yymmdd",//텍스트필드에 입력되는 날짜 형식
            maxDate: 0,//선택할수있는 최대날짜, ( 0 : 오늘 이후 날짜 선택 불가)
            onClose: function(selectedDate) {    
                // 종료일(endDate) datepicker가 닫힐때
                // 시작일(startDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 시작일로 지정
                $("#stdt").datepicker("option", "maxDate", selectedDate);
            }
	    });
	    //datepicker에서 선택된 시작일, 종료일 DayDataServlet으로 전달
	    
	});
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>일별 수질정보 조회</h2>
<div>
<p>조회 날짜 선택</p>
	시작일 <input type="text" id="stdt">
	종료일 <input type="text" id="eddt">
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
                <option value="11">서울특별시</option>
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
		</div>
    </form>
</div>
<%
	List<DayDTO> dList = (List<DayDTO>)session.getAttribute("dList");
	if(dList != null){
%>
<br>
<hr>
<h3>수질정보</h3>
<table border="1">
	<tr>
		<th>검사년월</th>
		<th>냄새(적부)</th>
		<th>맛(적부)</th>
		<th>색도(5이하)</th>
		<th>pH(5.8~8.%)</th>
		<th>탁도(0.5이하)</th>
		<th>잔류염소(4이하)</th>
	</tr>
<%
		for(int i = 0; i < dList.size(); i++){
			DayDTO dDTO = dList.get(i);
			String date = dDTO.getDate();
			String taste = dDTO.getTaste();
			String smell = dDTO.getSmell();
			String chromaticity = dDTO.getChromaticity();
			String pH = dDTO.getpH();
			String turbidity = dDTO.getTurbidity();
			String residualCI = dDTO.getResidualCI();
%>
	<tr>
		<td><%= date %></td>
		<td><%= taste %></td>
		<td><%= smell %></td>
		<td><%= chromaticity %></td>
		<td><%= pH %></td>
		<td><%= turbidity %></td>
		<td><%= residualCI %></td>
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