package com.djm.apicommon.service;

import com.djm.apicommon.model.entity.UserInterfaceInfo;

/**
 * 内部用户接口信息服务
 */
public interface InnerUserInterfaceInfoService {

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);

    /**
     * 是否还有调用次数
     * @param interfaceId
     * @param usrId
     * @return
     */
    UserInterfaceInfo hasLeftNum(Long interfaceId, Long usrId);

    /**
     * 添加默认的用户接口信息
     * @param interfaceId
     * @param userId
     * @return
     */
    Boolean addDefaultUserInterfaceInfo(Long interfaceId, Long userId);
}
