package com.beyond.aop.weapon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.beyond.aop.annotation.NoLogging;

@Component
public class Sword extends Weapon {
	
	public Sword(@Value("크리스탈 소드") String name) {
		super(name);
	}

	@Override
	@NoLogging
	public String attack() {
		
		return "검을 휘두른다.";
	}

	@Override
	public String toString() {
		return "Sword [name=" + name + "]";
	}
}