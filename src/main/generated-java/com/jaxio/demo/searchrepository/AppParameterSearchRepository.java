package com.jaxio.demo.searchrepository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.jaxio.demo.domain.AppParameter;

public interface AppParameterSearchRepository extends ElasticsearchRepository<AppParameter, Integer> {

}
