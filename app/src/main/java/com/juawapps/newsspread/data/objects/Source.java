package com.juawapps.newsspread.data.objects;


@SuppressWarnings("unused")
public class Source {

    /**
     * id : abc-news-au
     * name : ABC News (AU)
     * description : Australia's most trusted source of local, national and world news. Comprehensive, independent, in-depth analysis, the latest business, sport, weather and more.
     * url : http://www.abc.net.au/news
     * category : general
     * language : en
     * country : au
     */

    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;

    public Source(String id, String name, String description, String url, String category,
                  String language, String country) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.language = language;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

}
