package com.example.edugateguru.Models;

public class Guru {
    String nama,kota,nip,email,telp,Pelajaran;

    public Guru(String nama, String kota, String nip, String email, String telp, String pelajaran) {
        this.nama = nama;
        this.kota = kota;
        this.nip = nip;
        this.email = email;
        this.telp = telp;
        Pelajaran = pelajaran;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getPelajaran() {
        return Pelajaran;
    }

    public void setPelajaran(String pelajaran) {
        Pelajaran = pelajaran;
    }
}
