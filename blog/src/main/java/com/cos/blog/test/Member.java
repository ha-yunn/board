package com.cos.blog.test;

import lombok.Builder;
//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //get,set을 동시에 실행, 옵션 종류 : @Data, @Getter, @Setter
//@AllArgsConstructor //public Member 부분을 대신해줌
@NoArgsConstructor

public class Member {

	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder //번호 자동 생성(생성자 이중으로 만들 필요 없음)
	public Member(int id, String username, String password, String email) { //id 내가 지정
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
//	public Member(String username, String password, String email) { //id 자동 할당
//		this.id = id;
//		this.username = username;
//		this.password = password;
//		this.email = email;
//	}
	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
	
	
}
