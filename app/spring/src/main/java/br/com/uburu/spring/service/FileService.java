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

import br.com.uburu.spring.document.File;
import br.com.uburu.spring.document.File.Line;
import br.com.uburu.spring.repository.FileRepository;

@Service
public class FileService {

    @Autowired
    private FileRepository repository;

    public File getFile(long id) {
        return repository.findById(id).orElse(null);
    }

    public File getFile(String path) {
        return repository.findByPathIgnoreCaseContaining(path).orElse(null);
    }

    public List<Line> getLines(long id) {
        var file = getFile(id);

        if (file != null) {
            return file.getLines();
        }

        return new ArrayList<>();
    }

    public List<Line> getLines(String path) {
        var file = getFile(path);

        if (file != null) {
            return file.getLines();
        }

        return new ArrayList<>();
    }

    public File save(File file) {
        return repository.save(file);
    }

    public File save(List<Line> lines, String path) {
        File file = new File();
        file.setLines(lines);
        file.setPath(path);

        return repository.save(file);
    }
    
}
