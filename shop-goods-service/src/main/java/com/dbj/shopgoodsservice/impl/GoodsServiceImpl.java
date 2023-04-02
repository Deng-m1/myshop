package com.dbj.shopgoodsservice.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.dbj.shopcommon.api.IGoodsService;
import com.dbj.shopcommon.constant.ShopCode;
import com.dbj.shopcommon.entity.Result;
import com.dbj.shopcommon.exception.CastException;
import com.dbj.shopcommon.pojo.TradeGoods;
import com.dbj.shopcommon.pojo.TradeGoodsNumberLog;

import com.dbj.shopgoodsservice.mapper.TradeGoodsMapper;
import com.dbj.shopgoodsservice.mapper.TradeGoodsNumberLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dbj.shopcommon
 * @create 2019/12/27/10:02
 */
@Component
@Service(interfaceClass = IGoodsService.class)
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private TradeGoodsMapper goodsMapper;

    @Autowired
    private TradeGoodsNumberLogMapper goodsNumberLogMapper;

    @Autowired
    private RedisTemplate<Long,Integer> redisTemplate;

    @Override
    public TradeGoods findOne(Long goodsId) {
        if (goodsId == null) {
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    /**
     * 扣减库存
     *
     * @param goodsNumberLog
     * @return
     */
    @Override
    public Result reduceGoodsNum(TradeGoodsNumberLog goodsNumberLog) {

        AtomicInteger f=new AtomicInteger(0);
        ValueOperations<Long,Integer> ops = redisTemplate.opsForValue();
        if (Boolean.FALSE.equals(redisTemplate.hasKey(goodsNumberLog.getGoodsId())))
        {
            TradeGoods goods = goodsMapper.selectByPrimaryKey(goodsNumberLog.getGoodsId());
            ops.set(goods.getGoodsId(),goods.getGoodsNumber());
        }

        if (goodsNumberLog == null ||
                goodsNumberLog.getGoodsNumber() == null ||
                goodsNumberLog.getOrderId() == null ||
                goodsNumberLog.getGoodsNumber() == null ||
                goodsNumberLog.getGoodsNumber().intValue() <= 0) {
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }

        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptText("local key1 = KEYS[1]\n"+
                "local num=tonumber(ARGV[1])\n"+
                "local value1 = tomumber(redis.call('get',key1))\n"+
                "if value1 >= num then\n"+
                "    redis.call('decrby',key1,num)\n"+
                "    return 1\n" +
                "else\n"+
                "    return 0\n"+
                "end");
        List<Long> keys= Collections.singletonList(goodsNumberLog.getGoodsId());
        Integer num=goodsNumberLog.getGoodsNumber();
        Long result = redisTemplate.execute(script,keys,num);

        if (Objects.equals(result, 1L))
        {


        }else{
            CastException.cast(ShopCode.SHOP_GOODS_NUM_NOT_ENOUGH);
        }


        /*TradeGoods goods = goodsMapper.selectByPrimaryKey(goodsNumberLog.getGoodsId());
        if(goods.getGoodsNumber()<goodsNumberLog.getGoodsNumber()){
            //库存不足
            CastException.cast(ShopCode.SHOP_GOODS_NUM_NOT_ENOUGH);
        }
        //减库存
        goods.setGoodsNumber(goods.getGoodsNumber()-goodsNumberLog.getGoodsNumber());
        goodsMapper.updateByPrimaryKey(goods);


        //记录库存操作日志
        goodsNumberLog.setGoodsNumber(-(goodsNumberLog.getGoodsNumber()));
        goodsNumberLog.setLogTime(new Date());
        goodsNumberLogMapper.insert(goodsNumberLog);*/

        return new Result(ShopCode.SHOP_SUCCESS.getSuccess(),ShopCode.SHOP_SUCCESS.getMessage());
    }
    public boolean incrementIfLessThan(String key1, String key2, long num) {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setScriptText("local key1 = KEYS[1]\n" +
                "local key2 = KEYS[2]\n" +
                "local num = tonumber(ARGV[1])\n" +
                "local value1 = tonumber(redis.call('get', key1))\n" +
                "local value2 = tonumber(redis.call('get', key2))\n" +
                "if value1 + num <= value2 then\n" +
                "    redis.call('incrby', key1, num)\n" +
                "    return 1\n" +
                "else\n" +
                "    return 0\n" +
                "end");

        List<String> keys = Arrays.asList(key1, key2);
        Long result = redisTemplate.execute(script, keys, num);

        return result != null && result == 1;
    }




}

