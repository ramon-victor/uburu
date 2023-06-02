/**
 *  @file KeywordService.java
 *  @author Dheovani Xavier da Cruz
 *
 *  Copyright 2023, Dheovani Xavier da Cruz.  All rights reserved.
 *  https://github.com/Dheovani/Uburu
 *  Use of this source code is governed by a MIT license
 *  that can be found in the License file.
 *
 *  Uburu
 */

package br.com.uburu.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.entity.Keyword;
import br.com.uburu.spring.repository.KeywordRepository;
import br.com.uburu.spring.service.KeywordService;

@Service
public class KeywordService {

    @Autowired
    private KeywordRepository repository;

    public List<Keyword> getAll() {
        return repository.findAll();
    }

    public Keyword findeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Keyword save(Keyword keyword) {
        return repository.save(keyword);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
}
