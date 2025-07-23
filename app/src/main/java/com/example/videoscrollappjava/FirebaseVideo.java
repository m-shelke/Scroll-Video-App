package com.example.videoscrollappjava;

public class FirebaseVideo {

    private String name,uid,videouri,search,description;

    public FirebaseVideo(String name, String uid, String videouri, String search, String description) {
        this.name = name;
        this.uid = uid;
        this.videouri = videouri;
        this.search = search;
        this.description = description;
    }

    public FirebaseVideo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVideouri() {
        return videouri;
    }

    public void setVideouri(String videouri) {
        this.videouri = videouri;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
