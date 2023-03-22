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

    public Tracker(String path, String searchCriteria) {
        this.path = path;

        filter = new SearchFilter(path, searchCriteria);
        reader = new Reader();
    }

    /**
     * Retorna um objeto JSON contendo o nome dos arquivos e 
     * @param String... extensionFilter filtro de extensões (opcional)
     * @return List<Criteria> 
     */
    public List<Criteria> getFiles(String... extensionFilter) {
        File file = new File(path);
        return file.isDirectory() ? readFilesFolder(extensionFilter) : readFile();
    }

    /**
     * Busca no diretório inteiro
     * @param String... extensionFilter
     * @return List<Criteria>
     */
    private List<Criteria> readFilesFolder(String... extensionFilter) {
        List<String> folders = filter.getValidFolders();
        String[] searchCriteria = filter.getSearchCriteria();

        try {
            for (String folder : folders) {
                for (String search : searchCriteria) {
                    File folderFile = new File(folder);
                    reader.listFilesForFolder(folderFile, search, extensionFilter);
                }
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
    private List<Criteria> readFile() {
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
