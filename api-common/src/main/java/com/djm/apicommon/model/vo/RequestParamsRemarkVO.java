package com.djm.apicommon.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求参数说明 视图
 */
@Data
public class RequestParamsRemarkVO implements Serializable {

    private static final long serialVersionUID = -6549477882078242340L;
    /**
     * id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;

    /**
     * 是否必须
     */
    private String isRequired;

    /**
     * 类型
     */
    private String type;

    /**
     * 说明
     */
    private String remark;
}
