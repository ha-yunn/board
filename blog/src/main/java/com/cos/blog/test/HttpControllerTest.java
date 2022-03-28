package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자 요청 -> 응답(HTML파일)
//@Controller

@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest"; //final 기호상수 대문자를 쓰겠다.
	
	/*
	@GetMapping("http/lombok")
	public String lombokTest() {
		Member m = new Member(1, "ezen", "1234", "email");
		System.out.println(TAG+"getter"+m.getId());
		m.setId(5000);
		System.out.println(TAG+"setter"+m.getId());
		return "lombok test 완료";
	}
	*/
	
	//build 사용시
	@GetMapping("http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ezen").email("ezen@naver.com").password("1234").build();
		System.out.println(TAG+"getter"+m.getId());
		m.setId(5000);
		System.out.println(TAG+"setter"+m.getId());
		return "lombok test 완료";
	}

	
	//인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다.
	//나머지 post, put, delete는 postman으로
	//http://localhost:8080/http/get(select)
	/*
	@GetMapping("http/get")
	public String getTest(@RequestParam int id, @RequestParam String username,
			@RequestParam String password, @RequestParam String email) {
		return "get 요청: " + id + "," + username + "," + password + "," + email;
	}
	*/
	
	@GetMapping("http/get")
	public String getTest(Member m) {
		//필드명을 몰라도 되고, 순서를 무시해도 상관없음.
		return "get 요청: " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	
	//http://localhost:8080/http/post(insert)
	@PostMapping("http/post")
	public String postTest(@RequestBody Member m) {
		return "post 요청: " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}	
	
	//http://localhost:8080/http/put(update)
	@PutMapping("http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청: " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	
	//http://localhost:8080/http/delete(delete)
	@DeleteMapping("http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
}
