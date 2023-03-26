package br.com.uburu.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uburu.spring.entity.Filter;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {}
