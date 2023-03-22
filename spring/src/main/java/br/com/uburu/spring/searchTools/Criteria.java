package br.com.uburu.spring.searchTools;

public class Criteria {

    private String keyWords;
    private String repos;
    private String extensionFilter;
    private int line;

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getRepos() {
        return repos;
    }

    public void setRepos(String repos) {
        this.repos = repos;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getExtensionFilter() {
        return extensionFilter;
    }

    public void setExtensionFilter(String extensionFilter) {
        this.extensionFilter = extensionFilter;
    }

}
