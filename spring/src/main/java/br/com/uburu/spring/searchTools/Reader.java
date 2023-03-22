package br.com.uburu.spring.searchTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    private List<Result> files;
    private boolean ignoreCase;

    public Reader(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        files = new ArrayList<>();
    }

    /**
     * Realiza a pesquisa em todos os arquivos de um determinado diretório
     * @param String folderName
     * @param String[] search
     * @param String... extensionFilter
     * @throws FileNotFoundException
     */
    public void listFilesForFolder(final File folder, String[] search, String... extensionFilter) throws FileNotFoundException {
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
        // Validação de arquivos específicos, sem extensão
        if (!fileName.contains(".")) return false;

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
     * Realiza a pesquisa em um arquivo específico quando há mais de um termo de pesquisa
     * @param String fileName
     * @param String... search
     * @throws FileNotFoundException
     */
    public void searchInFile(String fileName, String... search) throws FileNotFoundException {
        final File file = new File(fileName);
        final Scanner scanner = new Scanner(file);

        // Contar a quantidade de arquivos que forem encontrados
        int lineCount = 1;
        while (scanner.hasNextLine()) {
            List<Result> list = new ArrayList<>();
            
            String line = scanner.nextLine();
            for (String s : search) {
                if (ignoreCase) {
                    line = line.toLowerCase();
                    s = s.toLowerCase();
                }

                if (line.contains(s)) {
                    final Result foundFiles = new Result();
                    foundFiles.setKeyWords(s);
                    foundFiles.setRepos(fileName);
                    foundFiles.setLine(lineCount);

                    list.add(foundFiles);
                    lineCount ++;
                }
            }

            if (search.length == list.size()) {
                files.addAll(list);
            }
        }

        scanner.close();
    }

    /**
     * Retorna os arquivos encontrados
     * @return List<JSONObject>
     */
    public List<Result> getFiles() {
        return files;
    }

}