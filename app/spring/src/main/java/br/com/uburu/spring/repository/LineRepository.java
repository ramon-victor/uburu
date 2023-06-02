package br.com.uburu.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uburu.spring.entity.Line;

public interface LineRepository extends JpaRepository<Line, Long> {

    List<Line> findByContentContaining(String content);
    
    List<Line> findByContentIgnoreCaseContaining(String content);
    
}
