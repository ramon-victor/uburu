package br.com.uburu.spring.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import br.com.uburu.spring.document.Line;

public interface LineRepository extends ElasticsearchRepository<Line, String> {

    Iterable<Line> findByContentContaining(String content);
    
    Iterable<Line> findByContentIgnoreCaseContaining(String content);
    
}
