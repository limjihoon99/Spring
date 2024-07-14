package com.beyond.di.character;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.beyond.di.weapon.Weapon;

import lombok.Getter;
import lombok.ToString;

/*
 * properties 파일의 값을 읽어오는 방법
 * 
 * 1. @PropertySource을 사용하는 방법
 *   - Environment 객체를 사용해서 properties 파일에 설정된 값을 읽어온다.
 *   - 스프링 프로퍼티 플레이스 홀더를 사용해서 properties 파일에 설정된 값을 읽어온다. (${키:기본값})
 *   
 * 2. @PropertySource을 생략하는 방법
 *   - xml 설정 파일을 사용하는 경우에는 <context:property-placeholder /> 요소를 추가한다.
 */

@Getter
@ToString
@Component
// 3-2) Lombok 어노테이션 사용
// @RequiredArgsConstructor
// @PropertySource("classpath:character.properties")
public class Character {
	@Value("${character.name:홍길동}")
	private String name;
	
	@Value("${character.level:77}")
	private int level;
	
//	1. 필드에 빈을 주입받는 방법
	@Autowired
	@Qualifier("sword")
	private Weapon weapon;
	
//	2-2) Lombok 어노테이션 사용
//	@Setter(onMethod_ = @Autowired)
//	3-2) Lombok 어노테이션 사용
//	private final Weapon weapon;
	
//	2. Setter 메소드로 빈을 주입받는 방법
//	2-1) 직접 Setter 메소드를 생성
//	@Autowired
//	public void setWeapon(Weapon weapon) {
//		this.weapon = weapon;
//	}
	
//	3. 생성자로 빈을 주입받는 방법
//	3-1) 직접 생성자를 생성
//	public Character(Weapon weapon) {
//		this.weapon = weapon;
//	}
	
//	public Character(/* @Autowired */ Environment env) {
//		this.name = env.getProperty("character.name");
//		this.level = Integer.parseInt(env.getProperty("character.level"));
//	}	
}