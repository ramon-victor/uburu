package br.com.uburu.spring.service;

import java.util.List;

import br.com.uburu.spring.document.Keyword;

public interface KeywordService {

    List<Keyword> getAll();
    Keyword findById(long id);
    Keyword save(Keyword keyword);
    void delete(long id);
    
}
