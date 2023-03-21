package br.com.uburu.spring.searchTools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SearchFilterTest {

    public SearchFilter filter;
    public String validFolder;
    public String invalidFolder;

    @BeforeEach
    void setUp() {
        invalidFolder = "!INVALID\\FOLDER\\TEST";
        validFolder = "VALID\\FOLDER\\TEST";

        filter = new SearchFilter(validFolder + ";" + invalidFolder, "Uburu AND UBUNTU OR Uburu2 AND Teste AND   Teste2");
    }
    
    @Test
    void isValidFolderTest() {
        assert filter != null;

        assertTrue(filter.isValidFolder(validFolder));
        assertFalse(filter.isValidFolder(invalidFolder));
    }

    @Test
    void getValidFoldersTest() {
        assert filter != null;

        List<String> validFolders = filter.getValidFolders();
        assertFalse(validFolders.isEmpty());

        validFolders.forEach(folder -> {
            assertTrue(filter.isValidFolder(folder));
        });
    }
    
    @Test
    void getSearchCriteriaTest() {
        assert filter != null;
        
        String[][] criteria = filter.getSearchCriteria();
        assertEquals(criteria.length, 2);

        assertEquals(criteria[0][0], "Uburu");
        assertEquals(criteria[0][1], "UBUNTU");

        assertEquals(criteria[1][0], "Uburu2");
        assertEquals(criteria[1][1], "Teste");
        assertEquals(criteria[1][2], "Teste2");
    }
    
}
