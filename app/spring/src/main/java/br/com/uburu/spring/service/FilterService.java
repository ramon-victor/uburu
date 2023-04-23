package br.com.uburu.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.document.Filter;
import br.com.uburu.spring.repository.FilterRepository;

@Service
public class FilterService {

    @Autowired
    private FilterRepository repository;

    public List<Filter> getAll() {
        List<Filter> filters = new ArrayList<>();
        repository.findAll().forEach(filters::add);

        return filters;
    }

    public Filter findById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Filter save(Filter filter) {
        return repository.save(filter);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
    
}
