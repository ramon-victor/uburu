/**
 *  @file PathService.java
 *  @author Dheovani Xavier da Cruz
 *
 *  Copyright 2023, Dheovani Xavier da Cruz.  All rights reserved.
 *  https://github.com/Dheovani/Uburu
 *  Use of this source code is governed by a MIT license
 *  that can be found in the License file.
 *
 *  Uburu
 */

package br.com.uburu.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.entity.Path;
import br.com.uburu.spring.repository.PathRepository;
import br.com.uburu.spring.service.PathService;

@Service
public class PathService {

    @Autowired
    private PathRepository repository;

    public List<Path> getAll() {
        return repository.findAll();
    }

    public Path findeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Path save(Path path) {
        return repository.save(path);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
}
