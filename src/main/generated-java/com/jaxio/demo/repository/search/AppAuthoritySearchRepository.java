/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template angular-lab:springboot/src/main/java/repository/search/EntitySearchRepository.e.vm.java
 */
package com.jaxio.demo.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.jaxio.demo.domain.AppAuthority;

/**
 * Spring Data ElasticSearch repository for the entity named: AppAuthority.
 */
public interface AppAuthoritySearchRepository extends ElasticsearchRepository<AppAuthority, Integer> {
}