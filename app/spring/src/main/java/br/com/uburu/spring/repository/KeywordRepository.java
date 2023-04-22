package br.com.uburu.spring.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import br.com.uburu.spring.document.Keyword;

public interface KeywordRepository extends ElasticsearchRepository<Keyword, Long> {}
