package com.example.group4_project;

public class profiless {
    private static String nama;
    private static String email;
    private static String phone;

    public static String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
}

    public profiless(String nama, String email, String phone){
        this.nama = nama;
        this.email = email;
        this.phone = phone;
    }

}
