
package com.chanpay.cloud.common.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的MyMapper
 *
 */
public interface CustomBaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
