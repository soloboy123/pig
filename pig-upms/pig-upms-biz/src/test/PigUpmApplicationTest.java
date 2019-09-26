package test;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description TODO
 * @Authror zhang
 * @Date 2019/9/26 14:34
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PigUpmApplicationTest.class)
public class PigUpmApplicationTest {
	@Autowired
	private StringEncryptor stringEncryptor;

	@Test
	public void testEnvironmentProperties() {
//		2QwFcy2g745Iqe/4iITs5+wLOhw1fqvK
		System.out.println(stringEncryptor.decrypt("2QwFcy2g745Iqe/4iITs5+wLOhw1fqvK"));

	}
}
