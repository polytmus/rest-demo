package me.qebs.restdemo.callback;

public class Client {

    public static void main(String[] args) {
        RegisterContext context = new RegisterContext("XXXX", 90898L);
        Operator operator = new RegisterOperator();

        operator.operate(context, new RegiesterCallback());

    }
}
