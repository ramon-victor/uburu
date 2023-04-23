package br.com.uburu.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.document.Path;
import br.com.uburu.spring.repository.PathRepository;
import br.com.uburu.spring.service.PathService;

@Service
public class PathService {

    @Autowired
    private PathRepository repository;

    public List<Path> getAll() {
        List<Path> paths = new ArrayList<>();
        repository.findAll().forEach(paths::add);

        return paths;
    }

    public Path findById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Path save(Path path) {
        return repository.save(path);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
    
}
