package com.codingfairy.bl.vo;

import com.codingfairy.data.entity.UserEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Created by cuihao on 2017-05-16.
 * user vo
 */
@Data
public class UserVo {
    private int id;
    private String username;
    public UserVo(UserEntity entity) {
        BeanUtils.copyProperties(entity,this,"role","password");
    }
}
