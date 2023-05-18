/**
 *  @file KeywordController.java
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

import br.com.uburu.spring.document.Keyword;
import br.com.uburu.spring.service.KeywordService;

@RestController
@RequestMapping("/api/v1/keyword")
@CrossOrigin(origins = "http://localhost:3000")
public class KeywordController {

    @Autowired
    private KeywordService service;

    @GetMapping
    public ResponseEntity<List<Keyword>> getAll() {
        List<Keyword> keyword = service.getAll();
        return new ResponseEntity<List<Keyword>>(keyword, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Keyword> include(@RequestBody Keyword keyword) {
        service.save(keyword);
        return new ResponseEntity<Keyword>(keyword, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        service.deleteAll();
        return ResponseEntity.accepted().build();
    }
    
}
