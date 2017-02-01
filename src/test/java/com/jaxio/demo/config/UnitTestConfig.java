package com.jaxio.demo.config;

import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import com.jaxio.demo.repository.BookRepository;

public class UnitTestConfig {

	private final Logger log = LoggerFactory.getLogger(UnitTestConfig.class);
	
	@Bean
	public BookRepository bookRepository() {
		log.info("Dans bookRepository()");
		return Mockito.mock(BookRepository.class);
	}
}
