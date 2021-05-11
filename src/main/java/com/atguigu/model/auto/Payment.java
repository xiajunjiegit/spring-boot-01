package com.atguigu.model.auto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Payment extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 流水号
     */
    private String serial;


}
