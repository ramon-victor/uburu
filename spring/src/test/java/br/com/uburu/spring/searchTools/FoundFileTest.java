package br.com.uburu.spring.searchTools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.minidev.json.JSONObject;

public class FoundFileTest {

    public FoundFile foundFile;
    public JSONObject jsonFile;

    @BeforeEach
    void setUp() {
        foundFile = new FoundFile();
        jsonFile = new JSONObject();
    }

    @Test
    void toJSONTest() {
        foundFile.setFilePath("Teste");
        foundFile.setLine(10);

        jsonFile.put("filePath", "Teste");
        jsonFile.put("line", 10);

        assertEquals(foundFile.toJSON().toString(), jsonFile.toString());
    }
    
}
