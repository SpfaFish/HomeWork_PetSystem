package ysu.szx.sys.petsys;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ysu.szx.sys.petsys.Utils.JwtUtils;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class PetsysApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void clctesttime() {
		Instant instant = Instant.now();
		int currentTime = (int) (instant.getEpochSecond()); // 时间戳以秒为单位
		System.out.println("当前系统时间的整型值：" + currentTime);
	}
	@Test
	public void TtestJwt(){
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", 1);
//		claims.put("name", "test");
//		String jwt = Jwts.builder()
//				.signWith(SignatureAlgorithm.HS256, "password")//签名算法
//				.setClaims(claims)//自定义文件
//				.setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //1h
//				.compact();
		String jwt = JwtUtils.GetStringJwt(claims);
		System.out.println(jwt);
	}
}
