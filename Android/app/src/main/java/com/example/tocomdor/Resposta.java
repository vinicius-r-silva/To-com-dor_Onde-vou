package com.example.tocomdor;

import java.util.*;

public class Resposta {

    public enum EnumRes {
        SAFE, ISOLADO, CONSULTA, TESTE;
    }

    List<Boolean> resSN = new ArrayList<>();
    List<String> resText = new ArrayList<>();
    List<List<Integer>> resMulInt = new ArrayList<>();

    int result;

    double lat;
    double lon;

    String NSUS;

    public Resposta(){}

    public Resposta(double lat, double lon, String NSUS) {
        this.lat = lat;
        this.lon = lon;
        this.NSUS = NSUS;
    }

    public Resposta(List<Boolean> resSN, List<String> resText, List<List<Integer>> resMulInt, int result, double lag, double lon, String NSUS) {
        this.resSN = resSN;
        this.resText = resText;
        this.resMulInt = resMulInt;
        this.result = result;
        this.lat = lag;
        this.lon = lon;
        this.NSUS = NSUS;
    }

    public EnumRes calcRes(){
        int i = 0;
        int quantSin = 0;
        int age;
        int quantComorb;

        boolean cont = false;

        for(i = 0; i < resSN.size() - 4; i++) {
            if(resSN.get(i)) {
                quantSin++;
            }
        }

        for(; i < resSN.size() - 1; i++) {
            if(resSN.get(i)) {
                cont = true;
                break;
            }
        }

        age = Integer.parseInt(resText.get(0));
        quantComorb = resMulInt.get(0).size();

        switch (quantSin) {
            case 0:
                if(cont)
                    return EnumRes.ISOLADO;

                return EnumRes.SAFE;

            case 1:
                if((quantComorb > 0 && age > 60) || cont){
                    return EnumRes.CONSULTA;
                }

                return EnumRes.ISOLADO;

            case 2:
                if((quantComorb > 0 && age > 60) || cont){
                    return EnumRes.TESTE;
                }

                return EnumRes.CONSULTA;

            default:
                return EnumRes.TESTE;

        }
    }

    public String getResStr() {
        String res = "";
        int i;
        int j;

        for (i = 0; i < resSN.size(); i++) {
            if(resSN.get(i))
                res += "1";
            else
                res += "0";
        }

        res += "|";

        for (i = 0; i < resText.size(); i++) {
            res += resText.get(i);

            if(i != resText.size() - 1){
                res += "_";
            }
        }

        res += "|";

        for (i = 0; i < resMulInt.size(); i++) {
            for (j = 0; j < resMulInt.get(i).size(); j++){
                res += resMulInt.get(i).get(j);

                if(j != resMulInt.get(i).size() - 1);
                    res += ".";
            }

            if(i != resMulInt.size() - 1){
                res += "_";
            }
        }

        return res;
    }

    public List<Boolean> getResSN() { return resSN; }

    public void setResSN(List<Boolean> resSN) { this.resSN = resSN; }

    public List<String> getResText() { return resText; }

    public void setResText(List<String> resText) { this.resText = resText; }

    public int getResult() { return result; }

    public void setResult(int result) { this.result = result; }

    public double getLat() { return lat; }

    public void setLag(double lat) { this.lat = lat; }

    public double getLon() { return lon; }

    public void setLon(double lon) { this.lon = lon; }

    public String getNSUS() { return NSUS; }

    public void setNSUS(String NSUS) { this.NSUS = NSUS; }
}
