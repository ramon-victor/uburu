package br.com.uburu.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.uburu.spring.dto.KeywordDTO;
import br.com.uburu.spring.entity.Keyword;

@Mapper(componentModel = "spring")
public interface KeywordMapper {

    List<Keyword> mapListKeywordDTO(List<KeywordDTO> keyWord);
    List<KeywordDTO> mapListKeyword(List<Keyword> keyWord);

    Keyword mapKeywordDTO(KeywordDTO keyWord);
    KeywordDTO mapKeyword(Keyword keyWord);
    
}
