package br.com.uburu.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.dto.FilterDTO;
import br.com.uburu.spring.entity.Filter;
import br.com.uburu.spring.mapper.FilterMapper;
import br.com.uburu.spring.repository.FilterRepository;
import br.com.uburu.spring.service.FilterService;

@Service
public class FilterServiceImpl implements FilterService {

    @Autowired
    private FilterRepository repository;
    private FilterMapper mapper = Mappers.getMapper(FilterMapper.class);

    @Override
    public List<FilterDTO> getAll() {
        List<Filter> filters = repository.findAll();
        return mapper.mapListFilter(filters);
    }

    @Override
    public FilterDTO findById(long id) {
        Optional<Filter> filter = repository.findById(id);

        if (filter.isPresent()) {
            Filter entity = filter.get();
            return mapper.mapFilter(entity);
        }

        return null;
    }

    @Override
    public FilterDTO save(FilterDTO filter) {
        Filter entity = mapper.mapFilterDTO(filter);
        repository.save(entity);

        return mapper.mapFilter(entity);
    }

    @Override
    public void delete(long id) {
        Optional<Filter> filterEntity = repository.findById(id);

        if (filterEntity.isPresent()) {
            Filter filter = filterEntity.get();
            repository.delete(filter);
        }
    }
    
}
