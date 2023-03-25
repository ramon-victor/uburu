package br.com.uburu.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.dto.PathDTO;
import br.com.uburu.spring.entity.Path;
import br.com.uburu.spring.mapper.PathMapper;
import br.com.uburu.spring.repository.PathRepository;
import br.com.uburu.spring.service.PathService;

@Service
public class PathServiceImpl implements PathService {

    @Autowired
    private PathRepository repository;
    private PathMapper mapper = Mappers.getMapper(PathMapper.class);

    @Override
    public List<PathDTO> getAll() {
        List<Path> paths = repository.findAll();
        return mapper.mapListPath(paths);
    }

    @Override
    public PathDTO findById(long id) {
        Optional<Path> path = repository.findById(id);

        if (path.isPresent()) {
            Path entity = path.get();
            return mapper.mapPath(entity);
        }

        return null;
    }

    @Override
    public PathDTO save(PathDTO path) {
        Path entity = mapper.mapPathDTO(path);
        repository.save(entity);

        return mapper.mapPath(entity);
    }

    @Override
    public void delete(long id) {
        Optional<Path> pathEntity = repository.findById(id);

        if (pathEntity.isPresent()) {
            Path path = pathEntity.get();
            repository.delete(path);
        }
    }
    
}
