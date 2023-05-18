/**
 *  @file FilterController.java
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uburu.spring.document.Filter;
import br.com.uburu.spring.service.FilterService;

@RestController
@RequestMapping("/api/v1/filter")
@CrossOrigin(origins = "http://localhost:3000")
public class FilterController {

    @Autowired
    private FilterService service;

    @GetMapping
    public ResponseEntity<List<Filter>> getAll() {
        List<Filter> filters = service.getAll();
        return new ResponseEntity<List<Filter>>(filters, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Filter> include(@RequestBody Filter filter) {
        service.save(filter);
        return new ResponseEntity<Filter>(filter, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        service.deleteAll();
        return ResponseEntity.accepted().build();
    }
    
}
