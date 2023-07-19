package com.sugarpocket.im.service.user.web.dto;

import com.sugarpocket.im.common.model.BaseRequest;
import com.sugarpocket.im.service.user.dao.entity.TbUserEntity;
import lombok.Data;

import java.util.List;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/19 22:28
 */
@Data
public class ImportUserReq extends BaseRequest {
    private List<TbUserEntity> userData;
}
