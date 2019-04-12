package me.qebs.restdemo.callback;

import lombok.Builder;
import lombok.Data;

/**
 * 调用结果，可以作为回调的入参
 */
@Data
@Builder
public class OpResult {

    private String msg;

}
