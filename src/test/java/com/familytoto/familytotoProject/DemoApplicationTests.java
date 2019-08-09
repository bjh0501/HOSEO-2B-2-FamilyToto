package com.familytoto.familytotoProject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void bcryptTest() {
		String password = "Password";
		String password2 = "Password";
		// 위 문장은 아래와 같다. 숫자가 높아질수록 해쉬를 생성하고 검증하는 시간은 느려진다. 즉, 보안이 우수해진다. 하지만 그만큼 응답 시간이 느려지기 때문에 적절한 숫자를 선정해야 한다. 기본값은 10이다.
		String passwordHashed1 = BCrypt.hashpw(password, BCrypt.gensalt(10));
		String passwordHashed2 = BCrypt.hashpw(password2, BCrypt.gensalt(10));
		
		System.out.println(passwordHashed1);
		System.out.println(passwordHashed2);
		
		// 생성된 해쉬를 원래 비밀번호로 검증한다. 맞을 경우 true를 반환한다. 주로 회원 로그인 로직에서 사용된다.
		boolean isValidPassword = BCrypt.checkpw(password, passwordHashed1);
		System.out.println(isValidPassword);		
	}
}
