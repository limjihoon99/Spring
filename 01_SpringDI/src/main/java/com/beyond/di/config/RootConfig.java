package com.beyond.di.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Import(
	value = {
		OwnerConfig.class,
		PetConfig.class
	}
)
@ComponentScan("com.beyond.di")
public class RootConfig {
	
	@Bean
	public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = null;
		
		configurer = new PropertySourcesPlaceholderConfigurer();
		
//		configurer.setLocation(new ClassPathResource("character.properties"));
		configurer.setLocations(
			new ClassPathResource("character.properties"), 
			new ClassPathResource("driver.properties")
		);
		
		return configurer;
	}
}