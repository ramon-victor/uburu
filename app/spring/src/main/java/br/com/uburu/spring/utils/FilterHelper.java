package br.com.uburu.spring.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.uburu.spring.entity.Filter;
import br.com.uburu.spring.entity.Line;
import br.com.uburu.spring.entity.Path;

@Component
public final class FilterHelper {

    /**
     * Retorna a extensão de um aquivo
     * @param filename
     * @return
     */
    private Optional<String> getExtension(String filename) {
        return Optional.ofNullable(filename)
            .filter(f -> f.contains("."))
            .map(f -> f.substring(filename.lastIndexOf(".")));
    }

    /**
     * Remove os arquivos com caminhos inválidos
     * @param Path pathEntity
     * @param List<Line> lines
     * @return List<Line>
     */
    public List<Line> filterByPath(Path pathEntity, List<Line> lines) {
        if (pathEntity.getPath() == null || pathEntity.getPath().isEmpty()) return lines;

        List<Line> linesToRemove = new ArrayList<>();

        for (final String path : pathEntity.getPath().split(";")) {
            for (final Line line : lines) {
                if (path.charAt(0) == '!') {
                    if (line.getFile().getPath().equals(path)) {
                        linesToRemove.add(line);
                    }
                } else {
                    if (!line.getFile().getPath().equals(path)) {
                        linesToRemove.add(line);
                    }
                }
            }
        }

        lines.removeAll(linesToRemove);
        return lines;
    }

    /**
     * Remove os arquivos com caminhos inválidos, considerandos os subrepositórios
     * @param Path pathEntity
     * @param List<Line> lines
     * @param boolean subFolders
     * @return List<Line>
     */
    public List<Line> filterByPath(Path pathEntity, List<Line> lines, boolean subFolders) {
        if (pathEntity.getPath() == null || pathEntity.getPath().isEmpty()) return lines;
        if (!subFolders) return filterByPath(pathEntity, lines);

        List<Line> linesToRemove = new ArrayList<>();

        for (final String path : pathEntity.getPath().split(";")) {
            for (final Line line : lines) {
                if (path.charAt(0) == '!') {
                    if (line.getFile().getPath().contains(path)) {
                        linesToRemove.add(line);
                    }
                } else {
                    if (!line.getFile().getPath().contains(path)) {
                        linesToRemove.add(line);
                    }
                }
            }
        }

        lines.removeAll(linesToRemove);
        return lines;
    }

    /**
     * Remove os arquivos com extensões inválidas
     * @param Filter filterEntity
     * @param List<Line> lines
     * @return List<Line>
     */
    public List<Line> filterByExtension(Filter filterEntity, List<Line> lines) {
        if (filterEntity.getFilter() == null || filterEntity.getFilter().isEmpty()) return lines;

        List<Line> linesToRemove = new ArrayList<>();

        for (final String filter : filterEntity.getFilter().replaceAll("\\s","").split(";")) {
            for (final Line line : lines) {
                final String ext = getExtension(line.getFile().getPath()).orElse(null);
                if (!ext.contains(filter)) {
                    linesToRemove.add(line);
                }
            }
        }

        lines.removeAll(linesToRemove);
        return lines;
    }
    
}
