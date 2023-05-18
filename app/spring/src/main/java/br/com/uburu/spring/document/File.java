/**
 *  @file File.java
 *  @author Dheovani Xavier da Cruz
 *
 *  Copyright 2023, Dheovani Xavier da Cruz.  All rights reserved.
 *  https://github.com/Dheovani/Uburu
 *  Use of this source code is governed by a MIT license
 *  that can be found in the License file.
 *
 *  Uburu
 */

package br.com.uburu.spring.document;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import br.com.uburu.spring.utils.Indices;

@Document(indexName = Indices.FILE)
@Setting(settingPath = "static/es-setting.json")
public class File {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Object)
    private Map<Integer, String> lines;

    @Field(type = FieldType.Text)
    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<Integer, String> getLines() {
        return lines;
    }

    public void setLines(Map<Integer, String> lines) {
        this.lines = lines;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
