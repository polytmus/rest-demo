package me.qebs.restdemo.callback;

public interface Operator {

    OpResult operate(Context context);


    void operate(Context context, OpCallback callback);
}
