package br.com.uburu.spring.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.uburu.spring.dto.PathDTO;
import br.com.uburu.spring.entity.Path;

@Mapper(componentModel = "spring")
public interface PathMapper {

    List<Path> mapListPathDTO(List<PathDTO> path);
    List<PathDTO> mapListPath(List<Path> path);

    Path mapPathDTO(PathDTO path);
    PathDTO mapPath(Path path);
    
}
