package com.beyond.di.character;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.beyond.di.config.RootConfig;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = "classpath:spring/root-context.xml")
@ContextConfiguration(classes = RootConfig.class)
class CharacterTest {
	/*
	 * required는 빈 주입이 필수로 진행되어야 하는지 설정하는 옵션이다.(기본값: true)
	 *   - true일 경우 주입해야 하는 빈이 애플리케이션 컨텍스트에 존재하지 않으면 예외를 발생한다.
	 *   - false일 경우 주입해야 하는 빈이 애플리케이션 컨텍스트에 존재하지 않으면 null 주입한다.
	 */
	@Autowired(required = false)
	private Character character;
	
	@Value("${db.driver}")
	private String driver;
	
	@Value("${db.url}")
	private String url;

	@Test
	@Disabled
	void test() {
	}
	
	@Test
	void create() {
//		System.out.println(character);
		
		assertThat(character).isNotNull();
		assertThat(character.getName()).isNotNull();
		assertThat(character.getLevel()).isGreaterThan(0);
		assertThat(character.getWeapon()).isNotNull();	
	}

	@Test
	void propertiesTest() {
//		System.out.println(driver);
//		System.out.println(url);
		
		assertThat(driver).isNotNull();
		assertThat(url).isNotNull();
	}
}