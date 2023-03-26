package br.com.uburu.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.uburu.spring.dto.FilterDTO;
import br.com.uburu.spring.entity.Filter;

@Mapper(componentModel = "spring")
public interface FilterMapper {

    List<Filter> mapListFilterDTO(List<FilterDTO> filter);
    List<FilterDTO> mapListFilter(List<Filter> filter);

    Filter mapFilterDTO(FilterDTO filter);
    FilterDTO mapFilter(Filter filter);
    
}
