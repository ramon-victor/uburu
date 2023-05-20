/**
 *  @file FilterService.java
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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.document.Filter;
import br.com.uburu.spring.repository.FilterRepository;

@Service
public class FilterService {

    @Autowired
    private FilterRepository repository;

    public List<Filter> getAll() {
        List<Filter> filters = new ArrayList<>();
        repository.findAll().forEach(filters::add);

        return filters;
    }

    public Filter findeById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Filter save(Filter filter) {
        return repository.save(filter);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
    
}
