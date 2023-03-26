package br.com.uburu.spring.service;

import java.util.List;

import br.com.uburu.spring.dto.KeywordDTO;

public interface KeywordService {

    List<KeywordDTO> getAll();
    KeywordDTO findById(long id);
    KeywordDTO save(KeywordDTO keyword);
    void delete(long id);
    
}
