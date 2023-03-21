package br.com.uburu.spring.searchTools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReaderTest {

    public Reader reader;
    public String magicSearchCriteria;

    @BeforeEach
    void setUp() {
        reader = new Reader();
        magicSearchCriteria = "Uburu";
    }

    @Test
    void searchInFileTest() throws Exception {
        assert reader != null;

        final File file = new File("src\\test\\java\\br\\com\\uburu\\spring\\searchTools\\test.txt");
        String path = file.getAbsolutePath();
        reader.searchInFile(path, magicSearchCriteria);

        assertFalse(reader.getFiles().isEmpty());
    }

    @Test
    void searchInFileListTest() throws Exception {
        assert reader != null;

        final File file = new File("src\\test\\java\\br\\com\\uburu\\spring\\searchTools\\test.txt");
        String path = file.getAbsolutePath();

        String composedCriteria = "Uburu AND utilizada";
        SearchFilter filter = new SearchFilter(path, composedCriteria);

        String[] criteria = filter.getSearchCriteria();
        assertEquals(criteria.length, 2);

        reader.searchInFile(path, criteria);
        assertFalse(reader.getFiles().isEmpty());
    }

    @Test
    void listFilesForFolderTest() throws Exception {
        assert reader != null;

        final File file = new File("src\\test\\java\\br\\com\\uburu\\spring\\searchTools");
        reader.listFilesForFolder(file, magicSearchCriteria);

        assertFalse(reader.getFiles().isEmpty());
    }

    @AfterEach
    void clearFiles() {
        reader.clearFilesList();
        assertTrue(reader.getFiles().isEmpty());
    }
    
}
