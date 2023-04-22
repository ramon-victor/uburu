package br.com.uburu.spring.service;

import java.util.List;

import br.com.uburu.spring.document.Path;

public interface PathService {

    List<Path> getAll();
    Path findById(long id);
    Path save(Path path);
    void delete(long id);
    
}
