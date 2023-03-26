package br.com.uburu.spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uburu.spring.searchTools.Criteria;
import br.com.uburu.spring.searchTools.Result;
import br.com.uburu.spring.searchTools.Tracker;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    @PostMapping @GetMapping
    public ResponseEntity<List<Result>> search(@RequestBody Criteria criteria) {
        String path = criteria.getRepos();
        String search = criteria.getKeyWords();

        final Tracker tracker = new Tracker(path, search, criteria.shouldIgnoreCase());
        List<Result> result = tracker.getFiles(criteria.getExtensionFilter());

        return new ResponseEntity<List<Result>>(result, HttpStatus.OK);
    }
    
}
