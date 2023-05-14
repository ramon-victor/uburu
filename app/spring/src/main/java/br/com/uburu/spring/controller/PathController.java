/**
 *  @file PathController.java
 *  @author Dheovani Xavier da Cruz
 *
 *  Copyright 2023, Dheovani Xavier da Cruz.  All rights reserved.
 *  https://github.com/Dheovani/Uburu
 *  Use of this source code is governed by a MIT license
 *  that can be found in the License file.
 *
 *  Uburu
 */

package br.com.uburu.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uburu.spring.document.Path;
import br.com.uburu.spring.service.PathService;

@RestController
@RequestMapping("/api/v1/path")
public class PathController {

    @Autowired
    private PathService service;

    @GetMapping
    public ResponseEntity<List<Path>> getAll() {
        List<Path> paths = service.getAll();
        return new ResponseEntity<List<Path>>(paths, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Path> include(@RequestBody Path path) {
        if (path.getId() == 0) {
            service.save(path);
            return new ResponseEntity<Path>(path, HttpStatus.CREATED);
        }

        return ResponseEntity.badRequest().build();
    }
    
}
