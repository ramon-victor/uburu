package br.com.uburu.spring.searchTools;

import java.io.File;
import java.util.List;

import org.json.JSONObject;

public class SearchEngine {

    private String path;
    private SearchFilter filter;
    private Tracker tracker;

    public SearchEngine(String path, String searchCriteria) {
        this.path = path;

        filter = new SearchFilter(path, searchCriteria);
        tracker = new Tracker();
    }

    /**
     * Retorna um objeto JSON contendo o nome dos arquivos e 
     * @return List<JSONObject> 
     */
    public List<JSONObject> getFiles() {
        File file = new File(path);
        boolean isFolder = file.isDirectory();

        return isFolder ? readFilesFolder() : readFile();
    }

    private List<JSONObject> readFilesFolder() {
        return null;
    }

    private List<JSONObject> readFile() {
        try {
            String[][] searchMatrix = filter.getSearchCriteria();

            for (String[] searchList : searchMatrix) {
                tracker.searchInFile(path, searchList);
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }

        return tracker.getFiles();
    }

}
