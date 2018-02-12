package com.example.ekasubawa.datasekolah.model;

/**
 * Created by Eka Subawa on 09/02/2018.
 */

public class Sekolah {

    private String nama;
    private String nss;
    private String alamat;
    private String desa;
    private String rw;
    private String rt;
    private String no_tlp;
    private String no_fax;
    private String email;
    private String website;
    private String kepala_sekolah;
    private String image;

    public Sekolah(){

    }

    public Sekolah(String nama, String nss, String alamat, String desa, String rw, String rt, String no_tlp, String no_fax, String email, String website, String kepala_sekolah, String image) {
        this.nama = nama;
        this.nss = nss;
        this.alamat = alamat;
        this.desa = desa;
        this.rw = rw;
        this.rt = rt;
        this.no_tlp = no_tlp;
        this.no_fax = no_fax;
        this.email = email;
        this.website = website;
        this.kepala_sekolah = kepala_sekolah;
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getNo_tlp() {
        return no_tlp;
    }

    public void setNo_tlp(String no_tlp) {
        this.no_tlp = no_tlp;
    }

    public String getNo_fax() {
        return no_fax;
    }

    public void setNo_fax(String no_fax) {
        this.no_fax = no_fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getKepala_sekolah() {
        return kepala_sekolah;
    }

    public void setKepala_sekolah(String kepala_sekolah) {
        this.kepala_sekolah = kepala_sekolah;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
