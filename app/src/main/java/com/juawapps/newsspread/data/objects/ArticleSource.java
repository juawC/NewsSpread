package com.juawapps.newsspread.data.objects;

@SuppressWarnings("unused")
public class ArticleSource {
    /**
     * id : bbc-news
     * name : BBC News
     */

    private String id;
    private String name;

    public ArticleSource(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
