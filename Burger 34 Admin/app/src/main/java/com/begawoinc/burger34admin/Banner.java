package com.begawoinc.burger34admin;

public class Banner {
    private String itemImage;
    private String key;


    public Banner() {
    }

    public Banner(String itemImage) {
        this.itemImage = itemImage;
    }


    public String getItemImage(){return itemImage;}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
