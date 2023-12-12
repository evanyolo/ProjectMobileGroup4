package com.example.group4_project;

public class labourer {
    private  String labourer_id;

    public String getLabourer_id() {
        return labourer_id;
    }

    public void setLabourer_id(String labourer_id) {
        this.labourer_id = labourer_id;
    }

    public String getTeam_labourer() {
        return team_labourer;
    }

    public void setTeam_labourer(String team_labourer) {
        this.team_labourer = team_labourer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage_lab() {
        return image_lab;
    }

    public void setImage_lab(String image_lab) {
        this.image_lab = image_lab;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private String team_labourer;
    private String category;
    private String address;
    private String image_lab;
    private String information;
    private  int price;




    public labourer(String labourer_id
            , String team_labourer
                    , String image_lab
            , String category
            , String information
            , int price){


        this.labourer_id = labourer_id;
        this.team_labourer = team_labourer;
        this.image_lab = image_lab;
        this.category = category;
        this.information = information;
        this.price = price;
    }
}
