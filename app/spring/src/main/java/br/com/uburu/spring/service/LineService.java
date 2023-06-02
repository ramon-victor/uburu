package br.com.uburu.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uburu.spring.document.File;
import br.com.uburu.spring.document.Line;
import br.com.uburu.spring.repository.LineRepository;

@Service
public class LineService {

    @Autowired
    private LineRepository repository;

    public List<Line> findAll() {
        List<Line> lines = new ArrayList<>();
        repository.findAll().forEach(lines::add);

        return lines;
    }

    public List<Line> findByContent(String content, boolean ignoreCase) {
        var lines =  ignoreCase ? 
            repository.findByContentIgnoreCaseContaining(content) : 
            repository.findByContentContaining(content);

        List<Line> list = new ArrayList<>();
        lines.forEach(list::add);

        return list;
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
