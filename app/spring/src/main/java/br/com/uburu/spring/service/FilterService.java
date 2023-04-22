package br.com.uburu.spring.service;

import java.util.List;

import br.com.uburu.spring.document.Filter;

public interface FilterService {

    List<Filter> getAll();
    Filter findById(long id);
    Filter save(Filter filter);
    void delete(long id);
    
}
