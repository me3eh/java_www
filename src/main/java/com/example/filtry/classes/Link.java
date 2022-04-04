package com.example.filtry.classes;

public class Link {
    public int timesVisited;
    public String url;
    public Link(String url){
        this.url = url;
        this.timesVisited = 1;
    }
    public int addVisit(){
        ++timesVisited;
        return timesVisited;
    }
    public int getTimesVisited() {
        return timesVisited;
    }

    public String getUrl() {
        return url;
    }
}
