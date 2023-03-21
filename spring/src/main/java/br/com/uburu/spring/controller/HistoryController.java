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

import br.com.uburu.spring.dto.HistoryDTO;
import br.com.uburu.spring.service.HistoryService;

@RestController
@RequestMapping("/api/v1/history")
public class HistoryController {

    @Autowired
    private HistoryService service;

    @GetMapping
    public ResponseEntity<List<HistoryDTO>> getAll() {
        List<HistoryDTO> cities = service.getAll();
        return new ResponseEntity<List<HistoryDTO>>(cities, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HistoryDTO> insertHistory(@RequestBody HistoryDTO history) {
        if (history.getId() == 0) {
            service.save(history);
            return new ResponseEntity<HistoryDTO>(history, HttpStatus.CREATED);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HistoryDTO> delete(@PathVariable("id") long id) {
        HistoryDTO history = service.findById(id);
        
        if (history == null) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);
        return ResponseEntity.accepted().build();
    }
    
}
