package br.com.uburu.spring.searchTools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tracker {

    private String path;
    private String searchCriteria;
    private Reader reader;
    private Logger logger = LoggerFactory.getLogger(Tracker.class);

    public Tracker(String path, String searchCriteria, boolean ignoreCase) {
        this.path = path;
        this.searchCriteria = searchCriteria;

        reader = new Reader(ignoreCase);
    }

    /**
     * Retorna um objeto JSON contendo o nome dos arquivos e 
     * @param String... extensionFilter filtro de extensões (opcional)
     * @return List<Criteria> 
     */
    public List<Result> getFiles(String... extensionFilter) {
        List<String> folders = splitFolders(path);;

        try {
            for (String path : folders) {
                File file = new File(path);
                
                if (file.isDirectory()) {
                    reader.listFilesForFolder(file, defineSearchCriteria(searchCriteria), extensionFilter);
                } else {
                    reader.searchInFile(path, defineSearchCriteria(searchCriteria));
                }
            }
        } catch (final Exception e) {
            logger.error("Ocorreu um erro", e);
            return null;
        }

        return reader.getFiles();
    }

    /**
     * Divide os diretórios, definindo quais são válidos ou inválidos
     * @param String folders
     * @return List<String>
     */
    private List<String> splitFolders(String folders) {
        List<String> validFolders = new ArrayList<>();
        folders = folders.replace(" ", "");

        // O ponto e vírgula separa os diretórios
        String[] folderList = folders.split(";");

        for (String folder : folderList) {
            // O ponto de exclamação define os diretórios inválidos
            if (!folder.contains("!")) {
                validFolders.add(folder);
            }
        }

        return validFolders;
    }

    /**
     * Define a estrutura lógica dos critérios de pesquisa
     * @param String search
     * @return String[]
     */
    private String[] defineSearchCriteria(String search) {
        // O & separar os valores da pesquisa
        String[] searchCriteria = search.split("&");

        for (int i = 0; i < searchCriteria.length; i ++) {
            searchCriteria[i] = searchCriteria[i].strip();

            if (searchCriteria[i].contains("\"")) {
                int firstIndex = searchCriteria[i].indexOf("\"");
                int lastIndex = searchCriteria[i].lastIndexOf("\"");

                searchCriteria[i] = searchCriteria[i].substring(firstIndex + 1, lastIndex);
            }
        }

        return searchCriteria;
    }

}
