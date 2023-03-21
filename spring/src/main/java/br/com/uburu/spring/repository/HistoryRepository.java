package br.com.uburu.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.uburu.spring.entity.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findByKeyWordsIgnoreCaseContaining(@Param("keyWords") String keyWords);
    
}
