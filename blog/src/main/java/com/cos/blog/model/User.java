package com.cos.blog.model;

import java.sql.Timestamp;

// persistence 연속성
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더패턴
//ORM -> Java(다른언어) Object -> 테이블 매핑해주는 기술
@Entity //User 클래스가 MySQL에 테이블이 생성이 된다. create
//@DynamicInsert  //insert시에 null인 필드를 제외시켜 준다.

public class User {
	
	@Id // Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) // 프로젝트에서 4가지 전략 중 1개 DB의 넘버링 전략을 따라감.표
	private int id; //시퀀스, auto_increment            // 4가지 전략은 SEQUENCE, TABLE, AUTO 등이 있음
	
	@Column(unique=true, nullable=false, length=30) 
	private String username; // 아이디
	
	@Column(nullable=false, length=100) // length=100 : 123456 => 해쉬(비밀번호 암호화)코드로 암호가 길게 나타나기 때문
	private String password;
	
	@Column(nullable=false, length=50)
	private String email; // myEMail, my_email
	
	//DB는 RoleType 이라는 것이 없다.
	@Enumerated(EnumType.STRING) // Enum을 쓰는게 좋다 , ADMIN, USER, Manager
	private RoleType role; // Enum을 쓰는게 좋다 , ADMIN, USER
	
	//@ColumnDefault("'user'")
	//private String role;
	
	// 내가 직접 시간을 입력하려면 Timestamp.valueOf(localDateTime.now())
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;
}
