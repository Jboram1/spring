/**
 * login관련 loginCheck
 */

$(function(){
	
	let chkKeyUp=0;
	
	//회원가입버튼 클릭
	$("#saveBtn").click(function(){
		if(chkKeyUp!=1){
			alert("아이디 중복체크 하셔야 다음으로 진행가능합니다.");
			return false;
		}
		
		//ajax 시작
		$.ajax({
			url:"/member/mInsert",
			type:"post",
			data:$("#memberFrm").serialize(), //20개 id:aaa,pw:1111,name:홍길동,,,길게 쓰지 않고 form에 있는 데이터 한번에 넘김
			//data:{"id":$("#id").val()}, //데이터를 개별적으로 보냄(id따로pw따로)
			dataType:"text", //받는파일형태 - text,json,xml
			success:function(data){
				alert("성공");
				
				console.log("data : "+data);
				if(data=="가입완료"){
					alert("회원가입이 완료되었습니다.");
					location.href="/";
				}
			},
			error:function(){
				alert("실패");
			}
			
		});
		//ajax끝
		
		
		
		
		
		
	});
	
	//아이디 확인 버튼 클릭후 아이디가 수정되었는지 확인
	$("#id").keyup(function(){
		console.log("key up 생성");
		$("#chkTxt").text("아이디 중복체크를 하셔야 합니다.");
		$("#chkTxt").css({"color":"orange","font-weight":"700"});
		chkKeyUp=0;
	});
	
	
	
	$("#idCheckBtn").click(function(){
		//아이디 중복체크
		alert("아이디 중복 체크를 합니다.");
		console.log($("#id").val());
		
		//id가 있는지 체크
		if($("#id").val().length<1){
			alert("아이디를 입력하셔야 체크 가능합니다.");
			$("#id").focus();
			return false;
		}
		//ajax 시작
		$.ajax({
			url:"/member/idCheck",
			type:"post",
			data:{"id":$("#id").val()}, //데이터를 개별적으로 보냄(id따로pw따로)
			//data:$("#memberFrm").serialize(), //20개 id:aaa,pw:1111,name:홍길동,,,길게 쓰지 않고 form에 있는 데이터 한번에 넘김
			//contentType:"json", //내가 보내는 파일 형태
			dataType:"text", //받는파일형태 - text,json,xml
			success:function(data){
				if(data=="사용가능"){
					alert("아이디를 사용할 수 있습니다.");
					$("#chkTxt").text("아이디 사용가능");
					$("#chkTxt").css({"color":"blue","font-weight":"700"});
				}else{
					alert("아이디를 사용불가!");
					$("#chkTxt").text("아이디 사용불가능!");
					$("#chkTxt").css({"color":"red","font-weight":"700"});
				}
				console.log("data : "+data);
				chkKeyUp=1;
			},
			error:function(){
				alert("실패");
			}
			
		});
		//ajax끝
		
	});
});