package br.com.uburu.spring.searchTools;

import java.io.File;
import java.util.List;

import org.json.JSONObject;

public class Tracker {

    private String path;
    private SearchFilter filter;
    private Reader reader;

    public Tracker(String path, String searchCriteria) {
        this.path = path;

        filter = new SearchFilter(path, searchCriteria);
        reader = new Reader();
    }

    /**
     * Retorna um objeto JSON contendo o nome dos arquivos e 
     * @return List<JSONObject> 
     */
    public List<JSONObject> getFiles() {
        File file = new File(path);
        return file.isDirectory() ? readFilesFolder() : readFile();
    }

    /**
     * Busca no diretório inteiro
     * @return List<JSONObject>
     */
    private List<JSONObject> readFilesFolder() {
        List<String> folders = filter.getValidFolders();
        String[] searchCriteria = filter.getSearchCriteria();

        folders.forEach(folder -> {
            try {
                for (String search : searchCriteria) {
                    File folderFile = new File(folder);
                    reader.listFilesForFolder(folderFile, search);
                }
            } catch (final Exception e) {
                e.printStackTrace();
                return;
            }
        });

        return reader.getFiles();
    }

    /**
     * Busca num arquivo específico
     * @return List<JSONObject>
     */
    private List<JSONObject> readFile() {
        try {
            String[] searchMatrix = filter.getSearchCriteria();
            reader.searchInFile(path, searchMatrix);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }

        return reader.getFiles();
    }

}
