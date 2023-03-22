package br.com.uburu.spring.searchTools;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tracker {

    private String path;
    private SearchFilter filter;
    private Reader reader;
    private Logger logger = LoggerFactory.getLogger(Tracker.class);

    public Tracker(String path, String searchCriteria, boolean ignoreCase) {
        this.path = path;

        filter = new SearchFilter(path, searchCriteria);
        reader = new Reader(ignoreCase);
    }

    /**
     * Retorna um objeto JSON contendo o nome dos arquivos e 
     * @param String... extensionFilter filtro de extensões (opcional)
     * @return List<Criteria> 
     */
    public List<Result> getFiles(String... extensionFilter) {
        File file = new File(path);
        return file.isDirectory() ? readFilesFolder(extensionFilter) : readFile();
    }

    /**
     * Busca no diretório inteiro
     * @param String... extensionFilter
     * @return List<Criteria>
     */
    private List<Result> readFilesFolder(String... extensionFilter) {
        List<String> folders = filter.getValidFolders();

        try {
            for (String folder : folders) {
                File folderFile = new File(folder);
                reader.listFilesForFolder(folderFile, filter.getSearchCriteria(), extensionFilter);
            }
        } catch (final Exception e) {
            logger.error("Ocorreu um erro", e);
            return null;
        }

        return reader.getFiles();
    }

    /**
     * Busca num arquivo específico
     * @return List<Criteria>
     */
    private List<Result> readFile() {
        try {
            String[] searchMatrix = filter.getSearchCriteria();
            reader.searchInFile(path, searchMatrix);
        } catch (final Exception e) {
            logger.error("Ocorreu um erro: ", e);
            return null;
        }

        return reader.getFiles();
    }

}
