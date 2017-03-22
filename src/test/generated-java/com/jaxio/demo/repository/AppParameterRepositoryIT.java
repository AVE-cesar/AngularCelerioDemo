package com.jaxio.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.jaxio.demo.Application;
import com.jaxio.demo.domain.AppParameter;

@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DataJpaTest
public class AppParameterRepositoryIT {

	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppParameterRepository repository;

    @Test
    public void findByUsernameShouldReturnUser() {
    	AppParameter appParameter = new AppParameter();
    	appParameter.setDomain("JUNIT");
    	appParameter.setKey("KEY");
    	appParameter.setValue("VALUE");
    	
        this.entityManager.persist(appParameter);
        AppParameter appParameterFound = this.repository.findByDomainAndKey("JUNIT", "KEY");
        
        /*assertThat(appParameterFound.getDomain().equals("JUNIT"));
        assertThat(appParameterFound.getKey()).isEqualTo("KEY");*/
    }
}
