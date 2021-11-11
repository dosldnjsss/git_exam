
//jQuery 
$(function(){/*내용을 읽자마자 수행해라*/
	//회원가입
	$('#writeBtn').click(function(){/*javascript의 onclick = jQuery의 id쓰면 된다.*/
		/*코드*/
		$('#nameDiv').empty(); /*초기화*/
		$('#idDiv').empty();
		$('#pwdDiv').empty();
		$('#repwdDiv').empty();
		
/*		if($('#name').val() == ''){ id속성
			$('#nameDiv').html('이름을 입력하세요');
		}*/
		if($('input[name="name"]').val() == ''){/*name속성*/
			$('#nameDiv').html('이름을 입력하세요');
			$('#name').focus();
		}else if($('input[name="id"]').val() == ''){
			$('#idDiv').html('아이디를 입력하세요');
		}else if($('input[name="idDuplication"]').val()== 'idUncheck'){
			$('#idDiv').html('아이디 중복체크를 해주세요.');
		}else if($('input[name="pwd"]').val() == ''){
			$('#pwdDiv').html('비밀번호를 입력하세요');
		}else if($('input[name="repwd"]').val() != $('input[name="pwd"]').val()){
			$('#repwdDiv').html('비밀번호가 다릅니다.');
		}else{
			$('form[name="writeForm"]').submit();
		}
	});
	//로그인
	$('#loginBtn').click(function(){
		$('#idDiv').empty();
		$('#pwdDiv').empty();
		
		if($('input[name="id"]').val() == ''){
			$('#idDiv').html('아이디를 입력하세요');
		}else if($('input[name="pwd"]').val() == ''){
			$('#pwdDiv').html('비밀번호를 입력하세요');
		}else{
			$('form[name="loginForm"]').submit();
		}
	});
});

/*function checkLogin(){
	document.getElementById("idDiv").innerText="";
	document.getElementById("pwdDiv").innerText="";
	
	if(document.loginForm.id.value==""){
		document.getElementById("idDiv").innerText="아이디를 입력하세요.";
	}else if(document.loginForm.pwd.value==""){
		document.getElementById("pwdDiv").innerText="비밀번호를 입력하세요.";
	}else{
		document.loginForm.submit();
	}
}*/


//아이디 중복 체크 
function checkId(id){
	
	var id=document.getElementById("id").value;
	
	document.getElementById("idDiv").innerText="";
	
	if(id == ""){
		//alert("아이디를 입력하세요.");
		document.getElementById("idDiv").innerText="아이디를 입력하세요.";
	
	}else{
			window.open("http://localhost:8080/mvcmember/member/checkId.do?id="+id,
			"checkId",
			"width=300 height=500 top=200 left=700");
		/*window.open("/mvcmember/CheckIdService?id="+id,
		"checkId",//타켓 - 아무 단어나 쓰면 창이 중복 뜨는걸 막아준다. 
      	"width=300 height=500 to200 left=700");*/
	}
	
}

//아이디 중복 체크 후 결과 넘기기 
function closeIdCheck(){
   
   window.opener.document.writeForm.id.value= document.getElementById("useid").innerText;
	//열려있는 창들 중에서 값을 넘겨라 
	opener.document.writeForm.idDuplication.value="idCheck";
  	close();
	opener.writeForm.focus();
}
/*
$('#checkIdClose').click(function(){
	$('#id',opener.document).val($('#checkId').val());
	$('#check',opener.document).val($('#checkId').val());//중복체크 버튼을 눌렀는지 확인
	widow.close();
	$('#pwd',opener.document).focus();
});

*/
function inputIdChk(){
	document.writeForm.idDuplication.value ="idUncheck";
	console.log("check");
}

$('#zipcodeBtn').click(function(){
		window.open("/mvcmember/member/checkPost.do",
			"checkPost",
			"width=500 height=500 top=200 left=700");
});

$('.addressA').click(function(){//id속성이면 제일 첫번쨰 부분만 먹힌다. (그래서 class를 쓴다.)
	//alert($(this).text());//val() - value의 속성값을 가져와라. text속성으로 꺼내와라 - 주소
	//alert($(this).prop('tagName'));//prop(property): 현재 쓰는 태그를 가져온다. - A(태그)
	//alert($(this).parent().prop('tagName'));//나의 부모의 태그를 알아보고 싶다. -TD(태크)
	//alert($(this).parent().prev().prop('tagName'));//나의 앞의 형제의 태그를 알아보고싶다. 
	//alert($(this).parent().prev().text());//나의 앞의 형제의 값을 꺼내와라 - 우편번호
	//alert($(this).parent().next().text());//나의 뒤의 형제의 값을 꺼내와라
	
	$('#zipcode',opener.document).val($(this).parent().prev().text());
	$('#addr1',opener.document).val($(this).text());
	window.close();
	$('#addr2', opener.document).focus();
});

/*function checkPostClose(zipcode, address){
	opener.writeForm.zipcode.value=zipcode;
	opener.writeForm.addr1.value=address;
	window.close();
	opener.writeForm.addr2.focus();
}*/


