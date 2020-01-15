package com.example.edugateguru.Models;

public class Tugas {
    private String nama,desc,date,time,kelas;

    public Tugas(String nama, String desc, String date, String time, String kelas) {
        this.nama = nama;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.kelas = kelas;
    }

    public Tugas() {

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
}
