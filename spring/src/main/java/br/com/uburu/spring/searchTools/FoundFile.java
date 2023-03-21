package br.com.uburu.spring.searchTools;

import org.json.JSONObject;

public class FoundFile {

    private String filePath;
    private int line;

    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public JSONObject toJSON() {
        final JSONObject obj = new JSONObject();
        obj.put("filePath", filePath);
        obj.put("line", line);

        return obj;
    }
    
}
