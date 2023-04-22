package br.com.uburu.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.document.Path;
import br.com.uburu.spring.repository.PathRepository;
import br.com.uburu.spring.service.PathService;

@Service
public class PathServiceImpl implements PathService {

    private final PathRepository repository;

    @Autowired
    public PathServiceImpl(PathRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Path> getAll() {
        List<Path> paths = new ArrayList<>();
        repository.findAll().forEach(paths::add);

        return paths;
    }

    @Override
    public Path findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Path save(Path path) {
        return repository.save(path);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
    
}
