package br.com.uburu.spring.searchTools;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tracker {

    private SearchFilter filter;
    private Reader reader;
    private Logger logger = LoggerFactory.getLogger(Tracker.class);

    public Tracker(String path, String searchCriteria, boolean ignoreCase) {
        filter = new SearchFilter(path, searchCriteria);
        reader = new Reader(ignoreCase);
    }

    /**
     * Retorna um objeto JSON contendo o nome dos arquivos e 
     * @param String... extensionFilter filtro de extensões (opcional)
     * @return List<Criteria> 
     */
    public List<Result> getFiles(String... extensionFilter) {
        List<String> folders = filter.getValidFolders();

        try {
            for (String path : folders) {
                File file = new File(path);
                
                if (file.isDirectory()) {
                    reader.listFilesForFolder(file, filter.getSearchCriteria(), extensionFilter);
                } else {
                    reader.searchInFile(path, filter.getSearchCriteria());
                }
            }
        } catch (final Exception e) {
            logger.error("Ocorreu um erro", e);
            return null;
        }

        return reader.getFiles();
    } 

}
