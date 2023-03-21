package br.com.uburu.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.uburu.spring.dto.HistoryDTO;
import br.com.uburu.spring.entity.History;
import br.com.uburu.spring.mapper.HistoryMapper;
import br.com.uburu.spring.repository.HistoryRepository;
import br.com.uburu.spring.service.HistoryService;

public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository repository;

    @Autowired
    private HistoryMapper mapper;

    @Override
    public List<HistoryDTO> getAll() {
        List<History> historyList = repository.findAll();
        return mapper.mapListHistory(historyList);
    }

    @Override
    public HistoryDTO save(HistoryDTO history) {
        History entity = mapper.mapHistoryDTO(history);
        repository.save(entity);

        return mapper.mapHistory(entity);
    }

    @Override
    public void delete(long id) {
        Optional<History> cityEntity = repository.findById(id);
        
        if (cityEntity.isPresent()) {
            History city = cityEntity.get();
            repository.delete(city);
        }
    }
    
}
