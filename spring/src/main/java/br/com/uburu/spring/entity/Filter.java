package br.com.uburu.spring.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String extensionFilter;
    private Date date;

    public long getId() {
        return id;
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
