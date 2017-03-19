/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template angular-lab:springboot/src/main/java/repository/search/EntitySearchRepository.e.vm.java
 */
package com.jaxio.demo.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.jaxio.demo.domain.AppToken;

/**
 * Spring Data ElasticSearch repository for the entity named: AppToken.
 */
public interface AppTokenSearchRepository extends ElasticsearchRepository<AppToken, String> {
}
