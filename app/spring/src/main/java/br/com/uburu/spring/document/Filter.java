package br.com.uburu.spring.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import br.com.uburu.spring.utils.Indices;

@Document(indexName = Indices.FILTER)
@Setting(settingPath = "static/es-setting.json")
public class Filter {

    @Id
    @Field(type = FieldType.Keyword)
    private long id;
    
    @Field(type = FieldType.Text)
    private String filter;

    @Field(type = FieldType.Date)
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
