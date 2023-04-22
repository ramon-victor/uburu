package br.com.uburu.spring.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import br.com.uburu.spring.document.Path;

public interface PathRepository extends ElasticsearchRepository<Path, Long> {}
