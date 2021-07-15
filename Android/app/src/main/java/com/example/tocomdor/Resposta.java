package com.example.tocomdor;

import java.util.*;

public class Resposta {

    List<Boolean> resSN = new ArrayList<>();
    List<String> resText = new ArrayList<>();
    List<List<String>> resMul = new ArrayList<>();

    int result;

    double lag;
    double lon;

    String NSUS;

    public Resposta(double lag, double lon, String NSUS) {
        this.lag = lag;
        this.lon = lon;
        this.NSUS = NSUS;
    }

    public Resposta(List<Boolean> resSN, List<String> resText, List<List<String>> resMul, int result, double lag, double lon, String NSUS) {
        this.resSN = resSN;
        this.resText = resText;
        this.resMul = resMul;
        this.result = result;
        this.lag = lag;
        this.lon = lon;
        this.NSUS = NSUS;
    }

    public void addRes(List<String> res) {
        resMul.add(res);
    }

    public List<Boolean> getResSN() {
        return resSN;
    }

    public void setResSN(List<Boolean> resSN) {
        this.resSN = resSN;
    }

    public List<String> getResText() {
        return resText;
    }

    public void setResText(List<String> resText) {
        this.resText = resText;
    }

    public List<List<String>> getResMul() {
        return resMul;
    }

    public void setResMul(List<List<String>> resMul) {
        this.resMul = resMul;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public double getLag() {
        return lag;
    }

    public void setLag(double lag) {
        this.lag = lag;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getNSUS() {
        return NSUS;
    }

    public void setNSUS(String NSUS) {
        this.NSUS = NSUS;
    }
}
