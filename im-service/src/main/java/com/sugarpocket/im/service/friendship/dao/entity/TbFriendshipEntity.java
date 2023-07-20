package com.sugarpocket.im.service.friendship.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author sugar-pocket (2565477149@qq.com)
 * @date 2023/7/20 20:36
 */
@TableName(value ="tb_friendship")
@Data
public class TbFriendshipEntity implements Serializable {
    /**
     * app_id
     */
    private Integer appId;

    /**
     * send_id
     */
    private String sendId;

    /**
     * receiver_id
     */
    private String receiverId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 1正常 2删除
     */
    private Integer status;

    /**
     * 1正常 2拉黑
     */
    private Integer black;

    /**
     *
     */
    private Long createTime;

    /**
     *
     */
    private Long friendSequence;

    /**
     *
     */
    private Long blackSequence;

    /**
     * 来源
     */
    private String addSource;

    /**
     * 来源
     */
    private String extra;
}
