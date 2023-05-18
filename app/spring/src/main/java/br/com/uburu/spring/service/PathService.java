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

    public Path save(Path path) {
        return repository.save(path);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
    
}
