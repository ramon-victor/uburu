package br.com.uburu.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.entity.File;
import br.com.uburu.spring.entity.Line;
import br.com.uburu.spring.repository.LineRepository;

@Service
public class LineService {

    @Autowired
    private LineRepository repository;

    public List<Line> findAll() {
        return repository.findAll();
    }

    public List<Line> findByContent(String content, boolean ignoreCase) {
        return ignoreCase ? 
            repository.findByContentIgnoreCaseContaining(content) : 
            repository.findByContentContaining(content);
    }

    public Line save(Line line) {
        return repository.save(line);
    }

    public Line save(File file, String content, Integer number) {
        Line line = new Line();
        line.setFile(file);
        line.setContent(content);
        line.setLineNumber(number);

        return repository.save(line);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
    
}
