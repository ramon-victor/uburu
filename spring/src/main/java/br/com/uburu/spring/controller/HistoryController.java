package br.com.uburu.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uburu.spring.dto.FilterDTO;
import br.com.uburu.spring.dto.KeywordDTO;
import br.com.uburu.spring.dto.PathDTO;
import br.com.uburu.spring.service.FilterService;
import br.com.uburu.spring.service.KeywordService;
import br.com.uburu.spring.service.PathService;

@RestController
@RequestMapping("/api/v1/history")
public class HistoryController {

    @Autowired
    private FilterService filterService;

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private PathService pathService;

    @GetMapping("/filter")
    public ResponseEntity<List<FilterDTO>> getFilters() {
        List<FilterDTO> filters = filterService.getAll();
        return new ResponseEntity<List<FilterDTO>>(filters, HttpStatus.OK);
    }

    @GetMapping("/keyword")
    public ResponseEntity<List<KeywordDTO>> getKeywords() {
        List<KeywordDTO> keywords = keywordService.getAll();
        return new ResponseEntity<List<KeywordDTO>>(keywords, HttpStatus.OK);
    }

    @GetMapping("/path")
    public ResponseEntity<List<PathDTO>> getPaths() {
        List<PathDTO> paths = pathService.getAll();
        return new ResponseEntity<List<PathDTO>>(paths, HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<FilterDTO> insertFilter(@RequestBody FilterDTO filter) {
        if (filter.getId() == 0) {
            filter = filterService.save(filter);
            return new ResponseEntity<FilterDTO>(filter, HttpStatus.CREATED);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/keyword")
    public ResponseEntity<KeywordDTO> insertKeyword(@RequestBody KeywordDTO keyword) {
        if (keyword.getId() == 0) {
            keyword = keywordService.save(keyword);
            return new ResponseEntity<KeywordDTO>(keyword, HttpStatus.CREATED);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/path")
    public ResponseEntity<PathDTO> insertPath(@RequestBody PathDTO path) {
        if (path.getId() == 0) {
            path = pathService.save(path);
            return new ResponseEntity<PathDTO>(path, HttpStatus.CREATED);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/filter/{id}")
    public ResponseEntity<FilterDTO> deleteFilter(@PathVariable("id") long id) {
        FilterDTO filter = filterService.findById(id);
        
        if (filter == null) {
            return ResponseEntity.notFound().build();
        }

        filterService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/keyword/{id}")
    public ResponseEntity<KeywordDTO> deleteKeyword(@PathVariable("id") long id) {
        KeywordDTO keyword = keywordService.findById(id);

        if (keyword == null) {
            return ResponseEntity.notFound().build();
        }

        keywordService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/path/{id}")
    public ResponseEntity<PathDTO> deletePath(@PathVariable("id") long id) {
        PathDTO path = pathService.findById(id);

        if (path == null) {
            return ResponseEntity.notFound().build();
        }

        pathService.delete(id);
        return ResponseEntity.accepted().build();
    }
    
}
