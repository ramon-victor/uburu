package br.com.uburu.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uburu.spring.document.Keyword;
import br.com.uburu.spring.service.KeywordService;

@RestController
@RequestMapping("/api/v1/keyword")
public class KeywordController {

    private final KeywordService service;

    @Autowired
    public KeywordController(KeywordService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Keyword>> getAll() {
        List<Keyword> keyword = service.getAll();
        return new ResponseEntity<List<Keyword>>(keyword, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Keyword> getById(@PathVariable("id") long id) {
        Keyword keyword = service.findById(id);
        return new ResponseEntity<Keyword>(keyword, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Keyword> include(@RequestBody Keyword keyword) {
        if (keyword.getId() == 0) {
            service.save(keyword);
            return new ResponseEntity<Keyword>(keyword, HttpStatus.CREATED);
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Keyword> update(
        @RequestBody Keyword keyword,
        @PathVariable("id") long id
    ) {
        Keyword entity = service.findById(id);

        if (entity != null) {
            entity.setKeyWords(keyword.getKeyWords());
            entity.setDate(keyword.getDate());
            service.save(entity);

            return new ResponseEntity<Keyword>(entity, HttpStatus.OK);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Keyword> delete(@PathVariable("id") long id) {
        Keyword keyword = service.findById(id);
        
        if (keyword == null) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);
        return ResponseEntity.accepted().build();
    }
    
}
