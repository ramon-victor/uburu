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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.document.File;
import br.com.uburu.spring.repository.FileRepository;

@Service
public class FileService {

    @Autowired
    private FileRepository repository;

    public List<File> search() {
        List<File> files = new ArrayList<>();
        repository.findAll().forEach(files::add);

        return files;
    }

    public File save(File file) {
        return repository.save(file);
    }

    public File save(Map<Integer, String> lines, String path) {
        File file = new File();
        file.setLines(lines);
        file.setPath(path);

        return repository.save(file);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
    
}
