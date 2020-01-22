package com.example.edugateguru.Models;

public class Agenda  {

    private String tanggal,jamMulai,jamSelesai,pelajaran,materi,kelas,siswaTidakMasuk;

    public Agenda(String tanggal, String jamMulai, String jamSelesai, String pelajaran, String materi, String kelas, String siswaTidakMasuk) {
        this.tanggal = tanggal;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.pelajaran = pelajaran;
        this.materi = materi;
        this.kelas = kelas;
        this.siswaTidakMasuk = siswaTidakMasuk;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getPelajaran() {
        return pelajaran;
    }

    public void setPelajaran(String pelajaran) {
        this.pelajaran = pelajaran;
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getSiswaTidakMasuk() {
        return siswaTidakMasuk;
    }

    public void setSiswaTidakMasuk(String siswaTidakMasuk) {
        this.siswaTidakMasuk = siswaTidakMasuk;
    }
}
