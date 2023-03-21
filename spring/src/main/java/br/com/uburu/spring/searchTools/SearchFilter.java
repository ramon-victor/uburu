package br.com.uburu.spring.searchTools;

import java.util.ArrayList;
import java.util.List;

public class SearchFilter {

    /**
     * Diretórios permitidos
     */
    private List<String> validFolders;

    /**
     * Diretórios inválidos
     */
    private List<String> invalidFolders;

    private String[][] searchCriteria;

    public SearchFilter(String folder, String search) {
        splitFolders(folder);
        defineSearchCriteria(search);
    }

    /**
     * Divide os diretórios, definindo quais são válidos ou inválidos
     * @param String folders
     */
    private void splitFolders(String folders) {
        // Remover todos os espaços em branco
        folders = folders.replace(" ", "");

        validFolders = new ArrayList<>();
        invalidFolders = new ArrayList<>();

        // O ponto e vírgula separa os diretórios
        String[] folderList = folders.split(";");

        for (String folder : folderList) {
            // O ponto de exclamação define os diretórios inválidos
            if (folder.contains("!")) {
                invalidFolders.add(folder);
            } else {
                validFolders.add(folder);
            }
        }
    }

    /**
     * Define a estrutura lógica dos critérios de pesquisa
     * @param String search
     */
    private void defineSearchCriteria(String search) {
        // Remover todos os espaços em branco
        search = search.replace(" ", "");

        String[] searchList = search.split("OR");
        searchCriteria = new String[searchList.length][];

        for (int i = 0; i < searchList.length; i ++) {
            String sCrit = searchList[i];
            String[] andSearch = sCrit.split("AND");

            searchCriteria[i] = andSearch;
        }
    }

    /**
     * Informa se um diretório é válido
     * @param String folder
     * @return boolean
     */
    public boolean isValidFolder(String folder) {
        return validFolders.contains(folder) && !invalidFolders.contains(folder);
    }

    /**
     * Retorna os diretórios válidos
     * @return List<String>
     */
    public List<String> getValidFolders() {
        return validFolders;
    }

    public String[][] getSearchCriteria() {
        return searchCriteria;
    }
    
}
