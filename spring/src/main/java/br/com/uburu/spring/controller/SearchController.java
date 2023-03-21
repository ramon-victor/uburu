package br.com.uburu.spring.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uburu.spring.dto.HistoryDTO;
import br.com.uburu.spring.searchTools.Tracker;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    @PostMapping @GetMapping
    public ResponseEntity<List<JSONObject>> search(@RequestBody HistoryDTO criteria) {
        String path = criteria.getRepos();
        String search = criteria.getKeyWords();

        final Tracker tracker = new Tracker(path, search);
        return new ResponseEntity<List<JSONObject>>(tracker.getFiles(), HttpStatus.OK);
    }
    
}
