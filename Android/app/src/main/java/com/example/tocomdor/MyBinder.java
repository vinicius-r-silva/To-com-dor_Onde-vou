package com.example.tocomdor;

import android.os.Binder;

public class RespostaBinder extends Binder {
    private Resposta resposta;

    public RespostaBinder(Resposta res){
        resposta = res;
    }
}
