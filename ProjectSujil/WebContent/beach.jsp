<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dto.BeachDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<select id="do">
		<option value="" selected disabled hidden>도를 선택해주세요</option>
		<option name="dong" value="강원">강원</option>
		<option name="dong" value="경기">경기</option>
		<option name="dong" value="경남">경남</option>
		<option name="dong" value="경북">경북</option>
		<option name="dong" value="부산">부산</option>
		<option name="dong" value="울산">울산</option>
		<option name="dong" value="인천">인천</option>
		<option name="dong" value="전남">전남</option>
		<option name="dong" value="전북">전북</option>
		<option name="dong" value="제주">제주</option>
		<option name="dong" value="충남">충남</option>
	</select>
	<div id="map" style="padding-left:5%; width:20%; height:350px;"></div>
	<div id="gungu"></div>
	<div id="sta"></div>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e52deb42345160b5651e65eaab40031"></script>
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
	<script type="text/javascript">
	
	
		$("#do").on("change", function() {
			$.ajax({
				data : {
					dong : $("#do").val()
				},
				contentType : 'application/json',
				url : 'BeachSearchServlet',
				dataType : 'json',
				success : function(data) {
						hideMarkers();
						var s= "";
					for(var i =1; i<data.length; i++){
						addMarker(new kakao.maps.LatLng(data[i-1].경도, data[i-1].위도));
						s += "<li href='#' name=gungu>"+data[i-1].군구;
				
					}
						$("#gungu").html(s);
						console.log(data)
					
					/* $("#gungu").html(data) */
				}
			});
		});

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
	    mapOption = { 
	        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

	// 지도를 클릭했을때 클릭한 위치에 마커를 추가하도록 지도에 클릭이벤트를 등록합니다
	kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
	    // 클릭한 위치에 마커를 표시합니다 
	    addMarker(mouseEvent.latLng);             
	});

	// 지도에 표시된 마커 객체를 가지고 있을 배열입니다
	var markers = [];

	// 마커 하나를 지도위에 표시합니다 
	addMarker(new kakao.maps.LatLng(33.450701, 126.570667));

	// 마커를 생성하고 지도위에 표시하는 함수입니다
	function addMarker(position) {
	    
	    // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({
	        position: position
	    });

	    // 마커가 지도 위에 표시되도록 설정합니다
	    marker.setMap(map);
	    
	    // 생성된 마커를 배열에 추가합니다
	    markers.push(marker);
	}

	// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
	function setMarkers(map) {
	    for (var i = 0; i < markers.length; i++) {
	        markers[i].setMap(map);
	    }            
	}

	// "마커 보이기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에 표시하는 함수입니다
	function showMarkers() {
	    setMarkers(map)    
	}

	// "마커 감추기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에서 삭제하는 함수입니다
	function hideMarkers() {
	    setMarkers(null);    
	}
	
	var imageSrc = 'https://blogfiles.pstatic.net/MjAyMDExMDZfMjA1/MDAxNjA0NjQ4NDg4NTQ2.Ca2pYYRwKiBt_Wl6IUu8mcGhBmaaFB7C9mWp4Y-vry8g.YymanuMYm5v-CL0GURmuIgubH2xQnSrFKCUVEWbGDcIg.PNG.make100min2022/914688d4482c428ab8dadc36ba0a970b.png', // 마커이미지의 주소입니다    
    imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      
// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
    markerPosition = new kakao.maps.LatLng(37.54699, 127.09598); // 마커가 표시될 위치입니다

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition, 
    image: markerImage // 마커이미지 설정 
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);  
	</script>
</body>
</html>