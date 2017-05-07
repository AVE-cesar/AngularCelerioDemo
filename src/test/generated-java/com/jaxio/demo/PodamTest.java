package com.jaxio.demo;
import javax.annotation.Resource;

import org.junit.Test;

import com.jaxio.demo.domain.AppParameter;

import junit.framework.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class PodamTest {
	
	@Test
	public void fillAnObject() {
		PodamFactory factory = new PodamFactoryImpl();
		AppParameter pojo = factory.manufacturePojo(AppParameter.class);
		
		System.out.println(pojo.toString());
		Assert.assertNotNull("The pojo cannot be null!", pojo);

	}
}
