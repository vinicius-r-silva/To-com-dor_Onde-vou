package com.example.tocomdor;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Estabelecimento {
    String nome;
    String tel;
    String tipo;
    String endereco;
    String CNES;

    float lat;
    float lon;
    float dist;


    // public static void main(String[] args){
    // 	Main est =  new Main(2,2, 4, 10101, "unidade1", "002323", "UBS");
    // 	System.out.println(est);
    // }

    public Estabelecimento(String nome, String tel, String tipo, String CNES, String endereco, float lat, float lon, float dist) {
        this.nome = nome;
        this.tel = tel;
        this.tipo = tipo;
        this.endereco = endereco;

        this.CNES = CNES;

        this.lat = lat;
        this.lon = lon;
        this.dist = dist;
    }

    public Estabelecimento(JSONObject json){
        try {
            this.CNES = json.getString("cnes");
            this.nome = json.getString("nome");
            this.tel = json.getString("telefone");
            this.tipo = json.getString("tipo");
            this.endereco = json.getString("endereco");

            this.lat = Float.parseFloat(json.getString("latitude"));
            this.lon = Float.parseFloat(json.getString("longitude"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
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

    public String getCNES() {
        return this.CNES;
    }

    public void setCNES(String CNES) {
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

    public String getEndereco(){return this.endereco;}

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
