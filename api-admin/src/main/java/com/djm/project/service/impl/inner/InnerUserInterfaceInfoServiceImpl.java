package com.djm.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.djm.apicommon.model.entity.User;
import com.djm.apicommon.model.entity.UserInterfaceInfo;
import com.djm.apicommon.service.InnerUserInterfaceInfoService;
import com.djm.project.service.UserInterfaceInfoService;
import com.djm.project.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import javax.annotation.Resource;

/**
 * 内部用户接口信息服务实现类
 *
 */
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }

    /**
     * 是否还有调用次数
     * @param interfaceId
     * @param usrId
     * @return
     */
    @Override
    public UserInterfaceInfo hasLeftNum(Long interfaceId, Long usrId) {
        LambdaQueryWrapper<UserInterfaceInfo> userInterfaceInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userInterfaceInfoLambdaQueryWrapper.eq(UserInterfaceInfo::getInterfaceInfoId, interfaceId)
                .eq(UserInterfaceInfo::getUserId, usrId);
        return userInterfaceInfoService.getOne(userInterfaceInfoLambdaQueryWrapper);
    }

    /**
     * 添加默认的接口信息
     * @param interfaceId
     * @param userId
     * @return
     */
    @Override
    public Boolean addDefaultUserInterfaceInfo(Long interfaceId, Long userId) {
        UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo();
        userInterfaceInfo.setLeftNum(999);
        userInterfaceInfo.setInterfaceInfoId(interfaceId);
        userInterfaceInfo.setId(userId);
        return userInterfaceInfoService.save(userInterfaceInfo);
    }


}
