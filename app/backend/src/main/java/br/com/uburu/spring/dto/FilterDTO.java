package br.com.uburu.spring.dto;

import java.util.Date;

public class FilterDTO {

    private long id;
    private String extensionFilter;
    private Date date;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getExtensionFilter() {
        return extensionFilter;
    }
    
    public void setExtensionFilter(String extensionFilter) {
        this.extensionFilter = extensionFilter;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
}
