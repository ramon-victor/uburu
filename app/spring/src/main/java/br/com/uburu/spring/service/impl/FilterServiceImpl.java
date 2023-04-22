package br.com.uburu.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.document.Filter;
import br.com.uburu.spring.repository.FilterRepository;
import br.com.uburu.spring.service.FilterService;

@Service
public class FilterServiceImpl implements FilterService {

    private final FilterRepository repository;

    @Autowired
    public FilterServiceImpl(FilterRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Filter> getAll() {
        List<Filter> filters = new ArrayList<>();
        repository.findAll().forEach(filters::add);

        return filters;
    }

    @Override
    public Filter findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Filter save(Filter filter) {
        return repository.save(filter);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
    
}
