package com.example.diaries;

import org.w3c.dom.Text;

public class Data {
    private Integer id;
    private String title;
    private String content;
    public Data() {}

    public Integer getId(){return id;}

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String t) {
        this.title = t;
    }

    public String getContent(){return content;}

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
