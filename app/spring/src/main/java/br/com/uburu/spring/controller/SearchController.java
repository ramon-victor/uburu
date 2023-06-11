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

import br.com.uburu.spring.entity.Filter;
import br.com.uburu.spring.entity.Index;
import br.com.uburu.spring.entity.Keyword;
import br.com.uburu.spring.entity.Line;
import br.com.uburu.spring.entity.Path;
import br.com.uburu.spring.service.FileService;
import br.com.uburu.spring.service.IndexService;
import br.com.uburu.spring.service.LineService;
import br.com.uburu.spring.utils.Indexer;
import br.com.uburu.spring.utils.RequestParams;
import br.com.uburu.spring.utils.FilterHelper;

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
    private IndexService indexService;

    @Autowired
    private Indexer indexer;

    @GetMapping
    public ResponseEntity<List<Line>> getLines() {
        return new ResponseEntity<List<Line>>(lineService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/indices")
    public ResponseEntity<List<Index>> getIndices() {
        return new ResponseEntity<List<Index>>(indexService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/find")
    public ResponseEntity<List<Line>> findLines(@RequestBody RequestParams params) {
        final Path path = params.getPath();
        final Filter filter = params.getFilter();
        final Keyword keyword = params.getKeyword();
        final boolean subFolders = params.isSubFolders();
        final boolean ignoreCase = params.isIgnoreCase();

        final List<Line> lines = lineService.findByContent(keyword.getKeyword(), ignoreCase);
        FilterHelper.filterByExtension(filter, lines);
        FilterHelper.filterByPath(path, lines, subFolders);
        
        return new ResponseEntity<List<Line>>(lines, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> index(@RequestBody Path path) {
        try {
            indexService.save(path.getPath());
            indexer.index(path.getPath());
            return ResponseEntity.accepted().build();
        } catch (final Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        lineService.deleteAll();
        fileService.deleteAll();
        indexService.deleteAll();
        return ResponseEntity.accepted().build();
    }
    
}
