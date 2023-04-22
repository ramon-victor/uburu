package br.com.uburu.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.document.Keyword;
import br.com.uburu.spring.repository.KeywordRepository;
import br.com.uburu.spring.service.KeywordService;

@Service
public class KeywordServiceImpl implements KeywordService {

    private final KeywordRepository repository;

    @Autowired
    public KeywordServiceImpl(KeywordRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Keyword> getAll() {
        List<Keyword> keywords = new ArrayList<>();
        repository.findAll().forEach(keywords::add);

        return keywords;
    }

    @Override
    public Keyword findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Keyword save(Keyword keyword) {
        return repository.save(keyword);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
    
}
