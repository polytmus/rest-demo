package me.qebs.restdemo.callback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterContext implements Context {

    private String deviceCode;

    private Long deviceId;

}
