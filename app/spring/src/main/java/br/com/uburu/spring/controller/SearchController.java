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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uburu.spring.document.Filter;
import br.com.uburu.spring.document.Keyword;
import br.com.uburu.spring.document.Line;
import br.com.uburu.spring.document.Path;
import br.com.uburu.spring.service.FileService;
import br.com.uburu.spring.service.LineService;
import br.com.uburu.spring.utils.Indexer;
import br.com.uburu.spring.utils.Validator;

/**
 * A classe SearchController é responsável por administrar todas as pesquisas do aplicativo.
 * Todos os demais controladores são destinados apenas ao registro do histórico de pesquisas.
 */
@RestController
@RequestMapping("/api/v1/search")
@CrossOrigin(origins = "http://localhost:3000")
public class SearchController {

    @Autowired
    private FileService fileService;

    @Autowired
    private LineService lineService;

    @Autowired
    private Indexer indexer;

    @GetMapping
    public ResponseEntity<List<Line>> findLines(
        @RequestBody Keyword keyword,
        @RequestBody Filter filter,
        @RequestBody Path path,
        @RequestBody Boolean subFolders,
        @RequestBody boolean ignoreCase
    ) {
        List<Line> lines = lineService.findByContent(keyword.getKeyword(), ignoreCase);
        lines = Validator.removeInvalidExtensionFiles(filter, lines);

        // Se for pra considerar os subrepositórios, retorna a lista inteira
        if (subFolders) return new ResponseEntity<List<Line>>(lines, HttpStatus.OK);

        lines = Validator.removeInvalidLines(path, lines);
        return new ResponseEntity<List<Line>>(lines, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> index(@RequestBody Path path) {
        indexer.index(path.getPath());
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        fileService.deleteAll();
        lineService.deleteAll();
        return ResponseEntity.accepted().build();
    }
    
}
