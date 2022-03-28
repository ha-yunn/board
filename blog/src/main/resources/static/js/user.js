// on함수 : 첫번째 파라미터 어떠한 이벤트가 일어날지 두번째 파라미터 무엇을 할지
let index = {
	init:function(){
		$("#btn-save").on("click",()=>{ //function(){} 대신 ()=>{} 사용하는 이유 : this를 바인딩하기 위해서!!!
			this.save();
		});
		$("#btn-update").on("click",()=>{ 
			this.update();
		});
	},
	
	save:function(){
		//alert("user의 save함수 호출됨");
		//username,password,email의 값(val)을 찾아서 data에 넣음
		let data={
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		};
		
		//console.log(data); //자바스크립트 오브젝트
		//console.log(JSON.stringify(data)); //JSON 문자열
		
		//ajax호출시 default가 비동기 호출
		//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!
		$.ajax({
			//회원가입 수행 요청
			type:"POST",
			url:"/auth/joinProc",
			data:JSON.stringify(data), //http body데이터가 어떤타입인지(MIME) , JSON 문자열
			contentType:"application/json; charset=utf-8",
			dataType:"json" //요청을 서버로해서 응답이 왔을때 기본적으로 모든것이 문자열 (생긴게 json이라면) => javascript오브젝트로 변경
		}).done(function(resp){
			if(resp.status === 500){
				alert("회원가입에 실패하였습니다.");
			}else{
				alert("회원가입이 완료되었습니다.");
				location.href="/";
			}
			
		}).fail(function(error){
			//실패일때
			alert(JSON.stringify(error));
		}); 
	},
	
	update:function(){
		let data={
			id:$("#id").val(),
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		};
		
		$.ajax({
			type:"PUT",
			url:"/user",
			data:JSON.stringify(data), 
			contentType:"application/json; charset=utf-8",
			dataType:"json" 
		}).done(function(resp){
			alert("회원수정이 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
}

index.init();