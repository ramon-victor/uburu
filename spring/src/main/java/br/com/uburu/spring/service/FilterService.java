package br.com.uburu.spring.service;

import java.util.List;

import br.com.uburu.spring.dto.FilterDTO;

public interface FilterService {

    List<FilterDTO> getAll();
    FilterDTO findById(long id);
    FilterDTO save(FilterDTO filter);
    void delete(long id);
    
}
