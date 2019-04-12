package me.qebs.restdemo.callback;

import java.util.concurrent.TimeUnit;

public class RegisterOperator implements Operator {

    @Override
    public OpResult operate(Context context) {
        System.out.println("同步调用，不带有回调");
        OpResult result = OpResult.builder().msg("同步调用返回结果").build();
        return result;
    }

    @Override
    public void operate(Context context, OpCallback callback) {
        Runnable r = () -> {
            try {
                System.out.println("准备开始注册");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("注册成功");
                OpResult result = OpResult.builder().msg(String.format("异步回调，注册人员成功，deviceCode:%s", context.getDeviceCode())).build();
                callback.callback(context, result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(r).start();

        System.out.println("调用结束，立即返回结果");
    }
}
