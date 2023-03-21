package br.com.uburu.spring.service;

import java.util.List;

import br.com.uburu.spring.dto.HistoryDTO;

public interface HistoryService {

    List<HistoryDTO> getAll();
    HistoryDTO save(HistoryDTO history);
    void delete(long id);
    
}
