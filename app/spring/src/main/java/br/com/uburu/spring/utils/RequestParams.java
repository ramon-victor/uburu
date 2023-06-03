package br.com.uburu.spring.utils;

import br.com.uburu.spring.entity.Filter;
import br.com.uburu.spring.entity.Keyword;
import br.com.uburu.spring.entity.Path;

public final class RequestParams {

    private Path path;
    private Filter filter;
    private Keyword keyword;
    private boolean subFolders;
    private boolean ignoreCase;

    public Path getPath() {
        return path;
    }
    
    public void setPath(Path path) {
        this.path = path;
    }
    
    public Filter getFilter() {
        return filter;
    }
    
    public void setFilter(Filter filter) {
        this.filter = filter;
    }
    
    public Keyword getKeyword() {
        return keyword;
    }
    
    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }
    
    public boolean isSubFolders() {
        return subFolders;
    }
    
    public void setSubFolders(boolean subFolders) {
        this.subFolders = subFolders;
    }
    
    public boolean isIgnoreCase() {
        return ignoreCase;
    }
    
    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }   
    
}
