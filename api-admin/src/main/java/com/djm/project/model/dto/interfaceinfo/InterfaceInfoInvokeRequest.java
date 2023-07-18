package com.djm.project.model.dto.interfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口调用请求
 *
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求参数
     *
     */
    private String requestParam;
    /**
     * 主机号
     */
    private String host;
}