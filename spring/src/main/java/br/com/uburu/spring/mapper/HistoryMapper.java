package br.com.uburu.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.uburu.spring.dto.HistoryDTO;
import br.com.uburu.spring.entity.History;

@Mapper(componentModel = "spring")
public interface HistoryMapper {
    
    List<History> mapListHistoryDTO(List<HistoryDTO> history);
    List<HistoryDTO> mapListHistory(List<History> history);

    History mapHistoryDTO(HistoryDTO history);
    HistoryDTO mapHistory(History history);

}
