package com.djm.apiinterface;

import com.djm.apiclientsdk.client.ApiClient;
import com.djm.apiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * 测试类
 */
@SpringBootTest
class ApiInterfaceApplicationTests {
	@Resource
	private ApiClient apiClient;

	@Test
	void contextLoads() throws UnsupportedEncodingException {
		String djm = apiClient.getNameByGet("djm");
		String djm1 = apiClient.getNameByPost("djm");
		User user = new User();
		user.setUsername("djm");
		String usernameByPost = apiClient.getUsernameByPost(user);
		System.out.println(djm);
		System.out.println(djm1);
		System.out.println(usernameByPost);
	}

}
