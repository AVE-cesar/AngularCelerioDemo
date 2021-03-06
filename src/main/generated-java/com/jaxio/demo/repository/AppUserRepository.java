/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template angular-lab:springboot/src/main/java/repository/EntityRepository.e.vm.java
 */
package com.jaxio.demo.repository;

import org.springframework.data.jpa.repository.*;

import com.jaxio.demo.domain.AppUser;

/**
 * AppUser repository.
 * 
 * @author generated by Celerio
 *
 */
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

	public AppUser findByLogin(String login);
}