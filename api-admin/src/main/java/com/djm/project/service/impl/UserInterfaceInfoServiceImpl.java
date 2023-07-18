package com.djm.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djm.apicommon.model.entity.User;
import com.djm.apicommon.model.entity.UserInterfaceInfo;
import com.djm.project.common.ErrorCode;
import com.djm.project.exception.BusinessException;
import com.djm.project.exception.ThrowUtils;
import com.djm.project.mapper.UserInterfaceInfoMapper;
import com.djm.project.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import com.djm.project.model.dto.userinterfaceinfo.UserInterfaceInfoQueryRequest;
import com.djm.project.service.UserInterfaceInfoService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户接口信息服务实现类
 *
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long userId = userInterfaceInfo.getUserId();
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        Integer totalNum = userInterfaceInfo.getTotalNum();
        Integer leftNum = userInterfaceInfo.getLeftNum();
        LambdaQueryWrapper<UserInterfaceInfo> userInterfaceInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userInterfaceInfoLambdaQueryWrapper
                .eq(UserInterfaceInfo::getUserId, userId)
                .eq(UserInterfaceInfo::getInterfaceInfoId, interfaceInfoId);
        List<UserInterfaceInfo> list = this.list(userInterfaceInfoLambdaQueryWrapper);
        if (!list.isEmpty()) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "该用户已经拥有该接口");
        }
        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(userId == null || interfaceInfoId == null, ErrorCode.PARAMS_ERROR);
        }
    }

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        // 判断
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("interfaceInfoId", interfaceInfoId)
                .eq("userId", userId)
                .setSql("leftNum = leftNum - 1, totalNum = totalNum + 1");
        return this.update(updateWrapper);
    }

    @Override
    public List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit) {
        return userInterfaceInfoMapper.listTopInvokeInterfaceInfo(limit);
    }

}




