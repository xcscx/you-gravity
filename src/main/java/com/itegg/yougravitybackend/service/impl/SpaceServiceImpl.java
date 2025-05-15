package com.itegg.yougravitybackend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.exception.ThrowUtils;
import com.itegg.yougravitybackend.model.dto.space.SpaceAddRequest;
import com.itegg.yougravitybackend.model.entity.Space;
import com.itegg.yougravitybackend.model.entity.User;
import com.itegg.yougravitybackend.model.enums.SpaceLevelEnum;
import com.itegg.yougravitybackend.service.SpaceService;
import com.itegg.yougravitybackend.mapper.SpaceMapper;
import com.itegg.yougravitybackend.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

/**
* @author ITegg
* @description 针对表【image_space(图库空间表)】的数据库操作Service实现
* @createDate 2025-05-09 13:53:00
*/
@Service
public class SpaceServiceImpl extends ServiceImpl<SpaceMapper, Space>
    implements SpaceService {

    @Resource
    private UserService userService;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Override
    public long addSpace(SpaceAddRequest request, User loginUser) {
        Space space = new Space();
        BeanUtils.copyProperties(request, space);
        // 填充默认值
        if(StrUtil.isBlank(request.getSpaceName())) {
            space.setSpaceName("默认空间");
        }
        if(request.getSpaceLevel() == null) {
            space.setSpaceLevel(SpaceLevelEnum.COMMON.getValue());
        }
        this.fillSpaceBySpaceLevel(space);
        this.validSpace(space, true);
        Long userId = loginUser.getId();
        space.setCreateBy(userId);
        // 权限校验
        if(SpaceLevelEnum.COMMON.getValue() != request.getSpaceLevel() && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "无权创建指定级别的空间");
        }
        // 针对用户进行加锁
        String lock = String.valueOf(userId).intern();
        synchronized (lock) {
            Long newSpaceId = transactionTemplate.execute(status -> {
                boolean exists = this.lambdaQuery().eq(Space::getCreateBy, userId).exists();
                ThrowUtils.throwIf(exists, ErrorCode.OPERATION_ERROR, "用户已经创建过空间");
                // 写入数据库
                boolean result = this.save(space);
                ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
                return space.getId();
            });
            return Optional.ofNullable(newSpaceId).orElse(-1L);
        }
    }

    @Override
    public void validSpace(Space space, boolean add) {
        ThrowUtils.throwIf(space == null, ErrorCode.PARAMS_ERROR);
        // 从对象中取值
        String spaceName = space.getSpaceName();
        Integer spaceLevel = space.getSpaceLevel();
        SpaceLevelEnum spaceLevelEnum = SpaceLevelEnum.getEnumByValue(spaceLevel);
        // 判断是否为创建
        if(add) {
            if(StrUtil.isBlank(spaceName)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "空间名称不能为空");
            }
            if(spaceLevel == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "空间等级不能为空");
            }
        }
        // 判断数据合理性
        if(spaceLevel != null && spaceLevelEnum != null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "空间等级不存在");
        }
        if(StrUtil.isNotBlank(spaceName) && spaceName.length() > 30) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "空间名称过长");
        }
    }

    @Override
    public void fillSpaceBySpaceLevel(Space space) {
        // 依据空间级别自动填充限额
        SpaceLevelEnum spaceLevelEnum = SpaceLevelEnum.getEnumByValue(space.getSpaceLevel());
        if(spaceLevelEnum != null) {
            long maxSize = spaceLevelEnum.getMaxSize();
            if(space.getMaxSize() == null) {
                space.setMaxSize(maxSize);
            }
            long maxCount = spaceLevelEnum.getMaxCount();
            if(space.getMaxCount() == null) {
                space.setMaxCount(maxCount);
            }
        }
    }

}


