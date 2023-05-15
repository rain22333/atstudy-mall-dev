package com.atstudy.service.impl;

import com.atstudy.entity.Category;
import com.atstudy.entity.bo.CategoryAddBo;
import com.atstudy.entity.bo.CategorySearchBo;
import com.atstudy.entity.bo.CategoryUpdateBo;
import com.atstudy.entity.bo.PageBo;
import com.atstudy.mapper.CategoryMapper;
import com.atstudy.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 分类表 业务逻辑层实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Integer> getIdListBySpuId(Long spuId) {
        return categoryMapper.getIdListBySpuId(spuId);
    }

    @Override
    public boolean updateByBo(CategoryUpdateBo updateBo) {
        // 创建用于更新数据的Category对象
        Category category = new Category();
        category.setCateId(updateBo.getCateId());
        category.setCateName(updateBo.getCateName());
        category.setCateParentid(updateBo.getCateParentid());
        category.setCateSort(updateBo.getCateSort());
        // 设置分类的更新时间
        category.setUpdatetime(LocalDateTime.now());

        // 调用持久层更新数据
        int i = categoryMapper.updateOne(category);

        return i >= 1;
    }

    @Override
    public Category getByCateId(Integer cateId) {

        return categoryMapper.getByCateId(cateId);
    }

    /**
     * 根据分类名称查询分类信息
     * @param cateName
     * @return
     */
    @Override
    public Category getByCateName(String cateName) {

        return categoryMapper.getByCateName(cateName);
    }

    /**
     * 根据筛选条件查询出分类列表
     * @param searchBo
     * @param pageBo
     * @return
     */
    @Override
    public List<Category> listBySearchBo(CategorySearchBo searchBo, PageBo pageBo) {

        // 先查询出满足条件的总记录数
        int count = categoryMapper.getCount(searchBo);
        // 给pageBo赋值，动态计算出总页数
        pageBo.setResultCount((long) count);
        // 再查询出满足条件的数据
        List<Category> categories = categoryMapper.listBySearchBo(searchBo, pageBo);

        return categories;
    }

    /**
     * 查询出所有分类
     * @return
     */
    @Override
    public List<Category> list() {

        List<Category> categories = categoryMapper.list();
        return categories;
    }

    /**
     * 添加一个分类
     * @param addBo
     * @return
     */
    @Override
    public boolean insertOne(CategoryAddBo addBo) {

        // 创建一个category对象
        Category category = new Category();
        category.setCateName(addBo.getCateName());
        category.setCateSort(addBo.getCateSort());
        category.setCateParentid(addBo.getCateParentid());
        // 设置创建更新时间
        LocalDateTime now = LocalDateTime.now();
        category.setCreatetime(now);
        category.setUpdatetime(now);

        int i = categoryMapper.insertOne(category);

        return i >= 1;
    }
}
