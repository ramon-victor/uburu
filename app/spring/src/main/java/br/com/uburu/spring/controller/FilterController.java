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

import br.com.uburu.spring.document.Filter;
import br.com.uburu.spring.service.FilterService;

@RestController
@RequestMapping("/api/v1/filter")
public class FilterController {

    @Autowired
    private FilterService service;

    @GetMapping
    public ResponseEntity<List<Filter>> getAll() {
        List<Filter> filters = service.getAll();
        return new ResponseEntity<List<Filter>>(filters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filter> getById(@PathVariable("id") long id) {
        Filter filter = service.findById(id);
        return new ResponseEntity<Filter>(filter, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Filter> include(@RequestBody Filter filter) {
        if (filter.getId() == 0) {
            service.save(filter);
            return new ResponseEntity<Filter>(filter, HttpStatus.CREATED);
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filter> update(
        @RequestBody Filter filter,
        @PathVariable("id") long id
    ) {
        Filter entity = service.findById(id);

        if (entity != null) {
            entity.setFilter(filter.getFilter());
            entity.setDate(filter.getDate());
            service.save(entity);

            return new ResponseEntity<Filter>(entity, HttpStatus.OK);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Filter> delete(@PathVariable("id") long id) {
        Filter filter = service.findById(id);
        
        if (filter == null) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);
        return ResponseEntity.accepted().build();
    }
    
}
