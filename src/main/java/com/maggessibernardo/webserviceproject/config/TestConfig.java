package com.maggessibernardo.webserviceproject.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.maggessibernardo.webserviceproject.entities.User;
import com.maggessibernardo.webserviceproject.repositories.UserRepository;

//classe de configuração para instanciar banco de dados para testes
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	@Autowired //(resolve dependencia e cria instancia de userRepository)
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
			//implementação da interface CommandLineRunner
			User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
			User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
			
			userRepository.saveAll(Arrays.asList(u1,u2));
	}
	
}
