$(function(){
	$('#writeBtn').click(function(){
		$('#subjectDiv').empty();//초기화 
		$('#contentDiv').empty();//초기화 
		
		if($('input[name="subject"]').val()==''){
			$('#subjectDiv').html('제목을 입력하세요');
			$('#subject').focus();
		}else if($('input[name="content"]').val()==''){
			$('#contentDiv').html('내용을 입력하세요');
			$('#content').focus();
		}else{
			$('form[name="boardWrite"]').submit();
		}
	});
});