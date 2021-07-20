package com.example.tocomdor;

import android.os.Binder;

public class MyBinder extends Binder {
    private Object object;

    public MyBinder(Object obj){
        object = obj;
    }

    public Object getObject(){
        return object;
    }
}
