package br.com.uburu.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.document.Keyword;
import br.com.uburu.spring.repository.KeywordRepository;
import br.com.uburu.spring.service.KeywordService;

@Service
public class KeywordService {

    @Autowired
    private KeywordRepository repository;

    public List<Keyword> getAll() {
        List<Keyword> keywords = new ArrayList<>();
        repository.findAll().forEach(keywords::add);

        return keywords;
    }

    public Keyword findById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Keyword save(Keyword keyword) {
        return repository.save(keyword);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
    
}
