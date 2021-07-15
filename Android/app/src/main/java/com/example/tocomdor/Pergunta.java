package com.example.tocomdor;
import java.util.*;

public class Pergunta {
    String per;
    List<String> res;

    public Pergunta(String per, List<String> res){
        this.per = per;
        this.res = new ArrayList<>(res);
    }

    public String getPer() {
        return per;
    }

    public List<String> getRes() {
        return res;
    }

    public void setRes(List<String> res) {
        this.res = new ArrayList<>(res);
    }

    public void setPer(String per) {
        this.per = per;
    }
}
