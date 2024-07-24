package com.example.service;

import com.example.entity.Goods;
import com.example.entity.GoodsStock;
import com.example.mapper.GoodsStockMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务处理
 **/
@Service
public class GoodsStockService {

    @Resource
    private GoodsStockMapper goodsStockMapper;

    @Resource
    private GoodsService goodsService;

    /**
     * 新增
     */
    @Transactional
    public void add(GoodsStock goodsStock) {
        // 查询到当前进货的商品的数量
        Integer num = goodsStock.getNum();
        Goods goods = goodsService.selectById(goodsStock.getGoodsId());
        goods.setStore(goods.getStore() + num);      // 修改一下啊  进货后的农产品数量
        goodsService.updateById(goods);       // 更新数据         农产品的数量
        goodsStockMapper.insert(goodsStock);   //插入进货的数据
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        goodsStockMapper.deleteById(id);
    }

    /**
     * 修改
     */
    public void updateById(GoodsStock goodsStock) {
        goodsStockMapper.updateById(goodsStock);
    }

    /**
     * 根据ID查询
     */
    public GoodsStock selectById(Integer id) {
        return goodsStockMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<GoodsStock> selectAll(GoodsStock goodsStock) {
        return goodsStockMapper.selectAll(goodsStock);
    }

    /**
     * 分页查询
     */
    public PageInfo<GoodsStock> selectPage(GoodsStock goodsStock, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsStock> list = goodsStockMapper.selectAll(goodsStock);
        return PageInfo.of(list);
    }

}