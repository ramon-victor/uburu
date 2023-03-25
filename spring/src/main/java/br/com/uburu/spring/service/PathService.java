package br.com.uburu.spring.service;

import java.util.List;

import br.com.uburu.spring.dto.PathDTO;

public interface PathService {

    List<PathDTO> getAll();
    PathDTO findById(long id);
    PathDTO save(PathDTO path);
    void delete(long id);
    
}
