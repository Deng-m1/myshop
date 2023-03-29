package com.dbj.shopuserservice.mapper;


import com.dbj.shopcommon.pojo.TradeUserMoneyLog;
import com.dbj.shopcommon.pojo.TradeUserMoneyLogExample;
import com.dbj.shopcommon.pojo.TradeUserMoneyLogKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TradeUserMoneyLogMapper {
    int countByExample(TradeUserMoneyLogExample example);

    int deleteByExample(TradeUserMoneyLogExample example);

    int deleteByPrimaryKey(TradeUserMoneyLogKey key);

    int insert(TradeUserMoneyLog record);

    int insertSelective(TradeUserMoneyLog record);

    List<TradeUserMoneyLog> selectByExample(TradeUserMoneyLogExample example);

    TradeUserMoneyLog selectByPrimaryKey(TradeUserMoneyLogKey key);

    int updateByExampleSelective(@Param("record") TradeUserMoneyLog record, @Param("example") TradeUserMoneyLogExample example);

    int updateByExample(@Param("record") TradeUserMoneyLog record, @Param("example") TradeUserMoneyLogExample example);

    int updateByPrimaryKeySelective(TradeUserMoneyLog record);

    int updateByPrimaryKey(TradeUserMoneyLog record);
}