package br.com.uburu.spring.searchTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    private List<Criteria> files;

    public Reader() {
        files = new ArrayList<>();
    }

    /**
     * Realiza a pesquisa em todos os arquivos de um determinado diretório
     * @param String folderName
     * @param String search
     * @param String... extensionFilter
     * @throws FileNotFoundException
     */
    public void listFilesForFolder(final File folder, String search, String... extensionFilter) throws FileNotFoundException {
        String root = folder.getAbsolutePath() + "\\";

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry, search, extensionFilter);
            } else {
                if (!validExtension(fileEntry.getName(), extensionFilter)) continue;
                searchInFile(root + fileEntry.getName(), search);
            }
        }
    }

    /**
     * Checa se a extensão do documento é válida
     * @param String fileName
     * @param String... filter
     * @return boolean
     */
    private boolean validExtension(String fileName, String... filter) {
        if (filter != null && filter.length > 0) {
            // A barra dupla é necessária para que o Java compreenda que o "." é a chave para separar as strings
            String extension = fileName.split("\\.")[1];
            
            for (int i = 0; i < filter.length; i ++) {
                if (filter[i].contains(extension)) return true;
            }

            return false;
        }

        return true;
    }

    /**
     * Realiza a pesquisa em um arquivo específico
     * @param String fileName
     * @param String search
     * @throws FileNotFoundException
     */
    public void searchInFile(String fileName, String search) throws FileNotFoundException {
        final File file = new File(fileName);
        final Scanner scanner = new Scanner(file);

        int lineCount = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.contains(search)) {
                final Criteria foundFiles = new Criteria();
                foundFiles.setKeyWords(search);
                foundFiles.setRepos(fileName);
                foundFiles.setLine(lineCount);

                this.files.add(foundFiles);
            }
            
            lineCount ++;
        }

        scanner.close();
    }

    /**
     * Realiza a pesquisa em um arquivo específico quando há mais de um termo de pesquisa
     * @param String fileName
     * @param String... search
     * @throws FileNotFoundException
     */
    public void searchInFile(String fileName, String... search) throws FileNotFoundException {
        final File file = new File(fileName);
        final Scanner scanner = new Scanner(file);

        boolean containsAllCriteria = true;
        for (String s : search) {
            boolean constainsSearch = false;

            int lineCount = 0;
            while (scanner.hasNextLine() && !constainsSearch) {
                String line = scanner.nextLine();

                if (line.contains(s)) {
                    final Criteria foundFiles = new Criteria();
                    foundFiles.setKeyWords(s);
                    foundFiles.setRepos(fileName);
                    foundFiles.setLine(lineCount);

                    this.files.add(foundFiles);
                    constainsSearch = true;
                }

                lineCount ++;
            }

            containsAllCriteria &= constainsSearch;
        }

        scanner.close();

        if (!containsAllCriteria) {
            clearFilesList();
        }
    }

    /**
     * Retorna os arquivos encontrados
     * @return List<JSONObject>
     */
    public List<Criteria> getFiles() {
        return files;
    }

    /**
     * Limpa a lista de arquivos
     */
    public void clearFilesList() {
        files.clear();
    }

}