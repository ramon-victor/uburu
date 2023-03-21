package br.com.uburu.spring.searchTools;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrackerTest {

    public Tracker tracker;
    public String magicSearchCriteria;

    @BeforeEach
    void setUp() {
        tracker = new Tracker();
        magicSearchCriteria = "Uburu";
    }

    @Test
    void searchInFileTest() throws Exception {
        assert tracker != null;

        final File file = new File("src\\test\\java\\br\\com\\uburu\\spring\\searchTools\\test.txt");
        String path = file.getAbsolutePath();
        tracker.searchInFile(path, magicSearchCriteria);

        assertFalse(tracker.getFiles().isEmpty());
    }

    @Test
    void searchInFileListTest() throws Exception {
        assert tracker != null;

        final File file = new File("src\\test\\java\\br\\com\\uburu\\spring\\searchTools\\test.txt");
        String path = file.getAbsolutePath();

        String composedCriteria = "BLABLABLA AND UBUNTU OR Uburu2 AND Teste AND   Teste2";
        SearchFilter filter = new SearchFilter(path, composedCriteria);
        for (String[] search : filter.getSearchCriteria()) {
            tracker.searchInFile(path, search);
            assertTrue(tracker.getFiles().isEmpty());

            tracker.clearFilesList();
        }

        composedCriteria = "Uburu AND utilizada OR palavra AND divertida";
        filter = new SearchFilter(path, composedCriteria);
        for (String[] search : filter.getSearchCriteria()) {
            tracker.searchInFile(path, search);
            assertFalse(tracker.getFiles().isEmpty());

            tracker.clearFilesList();
        }
    }

    @Test
    void listFilesForFolderTest() throws Exception {
        assert tracker != null;

        final File file = new File("src\\test\\java\\br\\com\\uburu\\spring\\searchTools");
        tracker.listFilesForFolder(file, magicSearchCriteria);

        assertFalse(tracker.getFiles().isEmpty());
    }

    @AfterEach
    void clearFiles() {
        tracker.clearFilesList();
        assertTrue(tracker.getFiles().isEmpty());
    }
    
}
