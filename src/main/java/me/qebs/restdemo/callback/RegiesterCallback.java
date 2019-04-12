package me.qebs.restdemo.callback;

public class RegiesterCallback implements OpCallback {
    @Override
    public void callback(Context context, OpResult result) {
        System.out.println("回调方法，result=" + result.getMsg());
    }
}
