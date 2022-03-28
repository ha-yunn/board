package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. ioC를 해준다.
@Service //서비스를 쓰는 이유 -> 1.트랜잭션 관리 2.서비스의 의미 때문
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder; //DI가 되어서 주입이 됨
	
	@Transactional 
	public void 회원가입(User user) { 
		String rawPassword = user.getPassword(); //1234 원문
		String encPassword = encoder.encode(rawPassword); //해쉬
		user.setPassword(encPassword); //password가 해쉬password로 바뀜
		
		user.setRole(RoleType.USER); //user값에 없는 Role 추가
		userRepository.save(user); //하나의 트랜잭션 (여러개도 가능)
	}
	
	@Transactional
	public void 회원수정(User user) {
		//수정시에는 영속성컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
		//select를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서!!
		//영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려주거든요.
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		
		//회원수정 함수 종료시 = 서비스 종료시 = 트랜잭션 종료 = commit이 자동으로 됩니다.
		//영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
	}
}
