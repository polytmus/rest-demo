package me.qebs.restdemo.callback;

public abstract class DasInvokeTemplate {

    public OpResult invoke(Context context) {

        try {
            doInvoke(context);
            return OpResult.builder().msg("成功").build();
        } catch (RuntimeException e) {
            return OpResult.builder().msg("业务异常").build();
        } catch (Exception e) {
            return OpResult.builder().msg("系统异常").build();
        }
    }

    public abstract void doInvoke(Context context);
}
