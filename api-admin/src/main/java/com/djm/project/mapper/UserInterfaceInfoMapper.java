package com.djm.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.djm.apicommon.model.entity.UserInterfaceInfo;
import java.util.List;

/**
* @author HP
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2023-07-14 18:00:40
* @Entity generator.domain.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {
    /**
     * 获取接口调用排名前 n 的接口信息
     *
     * @param limit 前几名
     * @return List<InterfaceInfoVO>
     */
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}




