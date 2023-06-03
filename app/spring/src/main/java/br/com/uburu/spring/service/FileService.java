/**
 *  @file FileService.java
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

import br.com.uburu.spring.entity.File;
import br.com.uburu.spring.repository.FileRepository;

@Service
public class FileService {

    @Autowired
    private FileRepository repository;

    public File findByPath(String path) {
        return repository.findByPathIgnoreCaseContaining(path).orElse(null);
    }

    public List<File> findAll() {
        List<File> files = new ArrayList<>();
        repository.findAll().forEach(files::add);

        return files;
    }

    public File save(File file) {
        return repository.save(file);
    }

    public File save(String path, String name) {
        File file = new File();
        file.setPath(path);
        file.setName(name);

        return repository.save(file);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
    
}
