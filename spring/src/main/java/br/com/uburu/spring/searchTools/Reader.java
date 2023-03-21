package br.com.uburu.spring.searchTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

public class Reader {

    private List<JSONObject> files;

    public Reader() {
        files = new ArrayList<>();
    }

    /**
     * Realiza a pesquisa em todos os arquivos de um determinado diretório
     * @param String folderName
     * @param String search
     * @throws FileNotFoundException
     */
    public void listFilesForFolder(final File folder, String search) throws FileNotFoundException {
        String root = folder.getAbsolutePath() + "\\";

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry, search);
            } else {
                searchInFile(root + fileEntry.getName(), search);
            }
        }
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
                final JSONObject foundFiles = new JSONObject();
                foundFiles.put("filePath", fileName);
                foundFiles.put("line", lineCount);

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
                    final JSONObject foundFiles = new JSONObject();
                    foundFiles.put("filePath", fileName);
                    foundFiles.put("line", lineCount);

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
    public List<JSONObject> getFiles() {
        return files;
    }

    /**
     * Limpa a lista de arquivos
     */
    public void clearFilesList() {
        files.clear();
    }

}