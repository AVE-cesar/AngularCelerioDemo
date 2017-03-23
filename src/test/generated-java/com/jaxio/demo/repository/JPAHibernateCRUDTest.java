package com.jaxio.demo.repository;

import org.junit.Test;

import com.jaxio.demo.domain.AppParameter;

import java.util.List;

import javax.persistence.Query;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JPAHibernateCRUDTest extends JPAHibernateTest {

	@Test
	public void testFindAllAppParameter_success () {
		List<AppParameter> params = findAllAppParameter();
		
		assertEquals(1, params.size());
	}

	
    @Test
    public void testGetObjectById_success() {
    	AppParameter param = em.find(AppParameter.class, 1);
        assertNotNull(param);
        
        assertTrue("SETTINGS".equals(param.getDomain()));
    }

    @Test
    public void testPersist_success() {
        em.getTransaction().begin();
        AppParameter param = new AppParameter();
        param.setDomain("UNITTEST");
        param.setKey("KEY");
        param.setValue("VALUE");
        em.persist(param);
        em.getTransaction().commit();

        List<AppParameter> params = findAllAppParameter();
        assertEquals(2, params.size());
        assertNotNull(params);
		AppParameter paramFound = em.find( AppParameter.class, param.getId());
		assertNotNull(paramFound);
    }

    @Test
    public void testDelete_success(){
    	AppParameter param = em.find(AppParameter.class, 1);

        em.getTransaction().begin();
        em.remove(param);
        em.getTransaction().commit();
        
        List<AppParameter> params = findAllAppParameter();

        assertNotNull(params);
        assertEquals(0, params.size());
    }
    
	private List<AppParameter> findAllAppParameter() {
		Query query = em.createQuery("SELECT e FROM AppParameter e", AppParameter.class);
		List<AppParameter> params = query.getResultList();
		return params;
	}
}
