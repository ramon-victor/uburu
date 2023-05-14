/**
 *  @file SearchController.java
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uburu.spring.document.File;
import br.com.uburu.spring.document.Filter;
import br.com.uburu.spring.document.Keyword;
import br.com.uburu.spring.document.Path;
import br.com.uburu.spring.service.FileService;
import br.com.uburu.spring.utils.CaseUtils;
import br.com.uburu.spring.utils.Indexer;

/**
 * A classe SearchController é responsável por administrar todas as pesquisas do aplicativo.
 * Todos os demais controladores são destinados apenas ao registro do histórico de pesquisas.
 */
@RestController
@RequestMapping("/api/v1/search")
@CrossOrigin(origins = "http://localhost:3000")
public class SearchController {

    @Autowired
    private FileService service;
    private final Indexer indexer = new Indexer();

    @GetMapping
    public ResponseEntity<List<File>> findFiles(
        @RequestBody Keyword keyword,
        @RequestBody Filter filter,
        @PathVariable boolean ignoreCase
    ) {
        List<String> keywords = List.of(keyword.getKeyWords().trim().split(","));
        List<File> results = service.search(keywords);

        if (!ignoreCase) {
            CaseUtils.dealWithCaseSensitiveness(results, keywords);
        }

        return new ResponseEntity<List<File>>(results, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> index(@RequestBody Path path) {
        indexer.index(path.getPath());
        return ResponseEntity.accepted().build();
    }
    
}
