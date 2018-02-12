package com.example.ekasubawa.datasekolah.model;

/**
 * Created by Eka Subawa on 07/02/2018.
 */

public class Desa {

    private String id;
    private String nama;
    private String id_kecamatan;

    public Desa(){

    }

    public Desa(String id, String nama, String id_kecamatan) {
        this.id = id;
        this.nama = nama;
        this.id_kecamatan = id_kecamatan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId_kecamatan() {
        return id_kecamatan;
    }

    public void setId_kecamatan(String id_kecamatan) {
        this.id_kecamatan = id_kecamatan;
    }
}
