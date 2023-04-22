package br.com.uburu.spring.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import br.com.uburu.spring.document.Filter;

public interface FilterRepository extends ElasticsearchRepository<Filter, Long> {}
