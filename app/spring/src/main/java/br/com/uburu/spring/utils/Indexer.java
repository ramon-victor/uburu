/**
 *  @file Indexer.java
 *  @author Dheovani Xavier da Cruz
 *
 *  Copyright 2023, Dheovani Xavier da Cruz.  All rights reserved.
 *  https://github.com/Dheovani/Uburu
 *  Use of this source code is governed by a MIT license
 *  that can be found in the License file.
 *
 *  Uburu
 */

package br.com.uburu.spring.utils;

import static br.com.uburu.spring.document.File.Line;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.uburu.spring.service.FileService;

/**
 * Classe responsável pela criação dos índices
 */
public class Indexer {

    @Autowired
    private FileService service;
    private static final Logger logger = LoggerFactory.getLogger(Indexer.class);

    /**
     * Cria o índice a partir de um arquivo
     * @param File entry
     */
    private void generateIndex(final File entry) {
        List<Line> lines = new ArrayList<>();
        Scanner scanner = null;
        
        try {
            scanner = new Scanner(entry);

            int cont = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(new Line(line, cont));

                cont ++;
            }
        } catch (final Exception e) {
            logger.error("Exception generating index:", e);
        } finally {
            scanner.close();
        }

        service.save(lines, entry.getAbsolutePath());
    }

    /**
     * Percorre os repositórios gerando os índices
     * @param File folder
     */
    public void index(final File folder) {
        for (final File entry : folder.listFiles()) {
            if (entry.isDirectory()) {
                index(entry);
            } else {
                generateIndex(entry);
            }
        }
    }

    /**
     * Percorre os repositórios gerando os índices
     * @param String path
     */
    public void index(final String path) {
        File folder = new File(path);
        index(folder);
    }
    
}
