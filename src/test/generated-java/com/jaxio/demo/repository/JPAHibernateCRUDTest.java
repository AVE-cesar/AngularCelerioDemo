package com.jaxio.demo.repository;

import org.junit.Test;

import com.jaxio.demo.domain.AppParameter;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.EntityType;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JPAHibernateCRUDTest extends JPAHibernateTest {

    @Test
    public void testGetObjectById_success() {
    	AppParameter param = em.find(AppParameter.class, 1);
        assertNotNull(param);
        
        System.out.println(param.toString());
        
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

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<" + param.getId());
        
        List<AppParameter> params = em.createNamedQuery("AppParameter.getAll", AppParameter.class).getResultList();
        assertEquals(2, params.size());
        assertNotNull(params);
		for (int i = 0; i < params.size(); i++) {
			System.out.println(params.get(i).toString());
		}
        
        AppParameter paramFound = em.find( AppParameter.class, param.getId());
        System.out.println(param.toString());
/*
        CriteriaQuery<AppParameter> query = new CriteriaQuery<AppParameter>() {

        };
        
        List<AppParameter> params = em.createQuery(query).getResultList();
  */      
    }

    @Test
    public void testDelete_success(){
    	AppParameter param = em.find(AppParameter.class, 1);

        em.getTransaction().begin();
        em.remove(param);
        em.getTransaction().commit();
        
        List<AppParameter> params = em.createNamedQuery("AppParameter.getAll", AppParameter.class).getResultList();

        assertNotNull(params);
		for (int i = 0; i < params.size(); i++) {
			System.out.println(params.get(i).toString());
		}
        assertEquals(0, params.size());
    }
}
