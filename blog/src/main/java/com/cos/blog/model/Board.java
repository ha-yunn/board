package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // auto-increment
	private int id;
	
	@Column(nullable=false, length=100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨.
	
	private int count; // 조회수 BoardService에 강제로 입력
	
	@ManyToOne(fetch=FetchType.EAGER) // Many = Board, User = One, 전략은 EAGER 즉시 가져옴, user는 1개니까
	@JoinColumn(name="userId")
	private User user;
	
								// 펼치기 사용해서 댓글 보이기 하려면 LAZY 전략 사용  //REMOVE : board게시글 지울때 거기 있는 댓글 전부 날리겠다.
	@OneToMany(mappedBy="board", fetch=FetchType.EAGER,cascade=CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아니다. 즉 포링키가 아니다.
	@JsonIgnoreProperties({"board"}) //board는 호출이 안 됨
	@OrderBy("id desc")
	private List<Reply> replys;				
	
	@CreationTimestamp
	private Timestamp createDate;

}
