package br.com.uburu.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.entity.Index;
import br.com.uburu.spring.repository.IndexRepository;

@Service
public class IndexService {

    @Autowired
    private IndexRepository repository;

    public List<Index> findAll() {
        return repository.findAll();
    }

    public Index save(Index index) {
        return repository.save(index);
    }

    public Index save(String path) {
        final Index index = new Index();
        index.setPath(path);

        return repository.save(index);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
}
