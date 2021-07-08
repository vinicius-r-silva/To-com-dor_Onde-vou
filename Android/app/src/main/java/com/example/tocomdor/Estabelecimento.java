package com.example.tocomdor;

import java.util.*;

public class Estabelecimento {
    String nome;
    String tel;
    String tipo;

    int CNES;

    float lat;
    float lon;
    float dist;


    // public static void main(String[] args){
    // 	Main est =  new Main(2,2, 4, 10101, "unidade1", "002323", "UBS");
    // 	System.out.println(est);
    // }

    public Estabelecimento(String nome, String tel, String tipo, int CNES, float lat, float lon, float dist) {

        this.nome = nome;
        this.tel = tel;
        this.tipo = tipo;

        this.CNES = CNES;

        this.lat = lat;
        this.lon = lon;
        this.dist = dist;
    }

    public String toString() {
        return "nome: " + this.nome + ", tel: " + this.tel + ", tipo: " + this.tipo
                + ", CNES: " + this.CNES + ", lat: " + lat + ", lon: " + lon + ", dist: " + dist;
    }

    public float getLat() {
        return this.lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return this.lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getDist() {
        return this.dist;
    }

    public void setDist(float dist) {
        this.dist = dist;
    }

    public int getCNES() {
        return this.CNES;
    }

    public void setCNES(int CNES) {
        this.CNES = CNES;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
