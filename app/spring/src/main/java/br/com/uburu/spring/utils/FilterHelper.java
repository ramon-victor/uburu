package br.com.uburu.spring.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.uburu.spring.entity.Filter;
import br.com.uburu.spring.entity.Line;
import br.com.uburu.spring.entity.Path;

public final class FilterHelper {

    /**
     * Remove os arquivos com caminhos inválidos
     * @param Path pathEntity
     * @param List<Line> lines
     * @return List<Line>
     */
    public static List<Line> filterByPath(Path pathEntity, List<Line> lines) {
        if (pathEntity.getPath() == null || pathEntity.getPath().isEmpty()) return lines;

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
    public static List<Line> filterByExtension(Filter filterEntity, List<Line> lines) {
        if (filterEntity.getFilter() == null || filterEntity.getFilter().isEmpty()) return lines;

        List<Line> linesToRemove = new ArrayList<>();

        for (final String filter : filterEntity.getFilter().split(";")) {
            for (final Line line : lines) {
                if (!line.getFile().getPath().contains(filter)) {
                    linesToRemove.add(line);
                }
            }
        }

        lines.removeAll(linesToRemove);
        return lines;
    }
    
}
