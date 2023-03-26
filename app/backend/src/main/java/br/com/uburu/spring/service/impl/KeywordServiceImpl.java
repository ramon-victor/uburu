package br.com.uburu.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.dto.KeywordDTO;
import br.com.uburu.spring.entity.Keyword;
import br.com.uburu.spring.mapper.KeywordMapper;
import br.com.uburu.spring.repository.KeywordRepository;
import br.com.uburu.spring.service.KeywordService;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private KeywordRepository repository;
    private KeywordMapper mapper = Mappers.getMapper(KeywordMapper.class);

    @Override
    public List<KeywordDTO> getAll() {
        List<Keyword> keywords = repository.findAll();
        return mapper.mapListKeyword(keywords);
    }

    @Override
    public KeywordDTO findById(long id) {
        Optional<Keyword> keyword = repository.findById(id);

        if (keyword.isPresent()) {
            Keyword entity = keyword.get();
            return mapper.mapKeyword(entity);
        }

        return null;
    }

    @Override
    public KeywordDTO save(KeywordDTO keyword) {
        Keyword entity = mapper.mapKeywordDTO(keyword);
        repository.save(entity);

        return mapper.mapKeyword(entity);
    }

    @Override
    public void delete(long id) {
        Optional<Keyword> cityEntity = repository.findById(id);
        
        if (cityEntity.isPresent()) {
            Keyword city = cityEntity.get();
            repository.delete(city);
        }
    }
    
}
