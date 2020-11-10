$("#title").on("focus", function(){
	$("#title").attr("placeholder", "")
});
$("#title").on("focusout", function(){
	$("#title").attr("placeholder", "10글자 이상 입력해주세요.")
});

$("#submit").on("click", function(event){
	if($("#title").val() < 10){
		alert("제목을 10글자 이상 입력해주세요.")
		$("#title").focus();
		return false;
	} else if($("#passwd").val()<4){
		alert("비밀번호 4자리를 입력해주세요.")
		$("#passwd").focus();
		return false;
	} else{
		var result=	confirm("작성하시겠습니까?")
		if(!result){
			return false;	
		} 	
	}
});

$(".userDel").on("click", function(){
	var num = $(this).attr("data");
	$("#userFixForm"+num).attr("action", "QnaUserDel")
});