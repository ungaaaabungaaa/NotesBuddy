package com.example.notesbuddy;
public class Upload {
    private String mName;
    private String mImageUrl;
    private String mKey;

    public Upload (){
    }

    public Upload(String name, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }


    public String getKey() {
        return mKey;
    }


    public void setKey(String key) {
        mKey = key;
    }
}

