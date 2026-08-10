package com.zzyl.base;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zzyl.utils.ConvertHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * 分页结果包装
 *
 * @author itheima
 */
@ApiModel(value = "分页数据消息体", description = "分页数据统一对象")
public class PageResponse<T> implements Serializable {

    @ApiModelProperty(value = "总条目数", required = true)
    private Long total = 0L;

    @ApiModelProperty(value = "页尺寸", required = true)
    private Integer pageSize = 0;

    @ApiModelProperty(value = "总页数", required = true)
    private Long pages = 0L;

    @ApiModelProperty(value = "页码", required = true)
    private Integer page = 0;

    @ApiModelProperty(value = "数据列表", required = true)
    private List<T> records = Collections.EMPTY_LIST;

    public PageResponse() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    /**
     * 返回一个分页对象实例
     */
    public static <T> PageResponse<T> getInstance() {
        return new PageResponse<>();
    }

    /**
     * IPage{@link IPage}对象封装为PageResponse,不封装 items 属性
     */
    public static <T> PageResponse<T> of(IPage<?> page) {
        PageResponse<T> result = new PageResponse<>();
        result.setPage(Convert.toInt(page.getCurrent()));
        result.setPageSize(Convert.toInt(page.getSize()));
        result.setPages(page.getPages());
        result.setTotal(page.getTotal());
        return result;
    }

    /**
     * IPage{@link IPage}对象封装为PageResponse,
     * 并将Page中的Records转换为指定类型封装为items
     */
    public static <T> PageResponse<T> of(IPage<?> page, Class<T> clazz) {
        return of(page, clazz, null);
    }

    /**
     * IPage{@link IPage}对象封装为PageResponse,
     * 并将Page中的Records转换为指定类型封装为items
     */
    public static <O, T> PageResponse<T> of(IPage<O> page, Class<T> clazz, ConvertHandler<O, T> convertHandler) {
        PageResponse<T> result = new PageResponse<>();
        result.setPage(Convert.toInt(page.getCurrent()));
        result.setPageSize(Convert.toInt(page.getSize()));
        result.setPages(page.getPages());
        result.setTotal(page.getTotal());
        result.setRecords(copyToList(page.getRecords(), clazz, convertHandler));
        return result;
    }

    /**
     * 对items进行类型转换
     */
    public static <O, T> PageResponse<T> of(PageResponse<O> origin, Class<T> clazz) {
        return of(origin, clazz, null);
    }

    /**
     * 对items进行类型转换
     */
    public static <O, T> PageResponse<T> of(PageResponse<O> origin, Class<T> clazz, ConvertHandler<O, T> convertHandler) {
        PageResponse<T> target = getInstance();
        BeanUtil.copyProperties(origin, target, "records");

        if (CollUtil.isEmpty(origin.getRecords())) {
            return target;
        }
        List<T> targetList = copyToList(origin.getRecords(), clazz, convertHandler);
        target.setRecords(targetList);

        return target;
    }

    /**
     * List{@link List}封装为分页数据对象
     */
    public static <T> PageResponse<T> of(List<T> items, Integer page, Integer pageSize, Long pages, Long counts) {
        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.setPage(Optional.ofNullable(page).orElse(1));
        pageResponse.setPageSize(Optional.ofNullable(pageSize).orElse(1));
        pageResponse.setPages(Optional.ofNullable(pages).orElse(1L));
        pageResponse.setTotal(Optional.ofNullable(counts).orElse(1L));

        if (CollUtil.isEmpty(items)) {
            return pageResponse;
        }

        pageResponse.setRecords(items);
        return pageResponse;
    }

    /**
     * List{@link List}封装为分页数据对象
     */
    public static <T> PageResponse<T> of(List<T> items) {
        return of(items, null, null, null, null);
    }

    /**
     * 返回包含任意数量元素的分页对象
     */
    public static <E> PageResponse<E> of(E... elements) {
        return of(Arrays.asList(elements));
    }

    /**
     * 对items进行类型转换
     */
    public static <O, T> PageResponse<T> of(PageResponse<O> origin, Function<List<O>, List<T>> function) {
        List<T> orderVOList = function.apply(origin.getRecords());
        return of(orderVOList, origin.getPage(), origin.getPageSize(), origin.getPages(), origin.getTotal());
    }

    /**
     * 转换结构
     */
    private static <T, O> List<T> copyToList(List<O> content, Class<T> clazz, ConvertHandler<O, T> convertHandler) {
        List<T> targetList = BeanUtil.copyToList(content, clazz);
        if (CollUtil.isNotEmpty(targetList) && ObjectUtil.isNotEmpty(convertHandler)) {
            for (int i = 0; i < content.size(); i++) {
                convertHandler.map(content.get(i), targetList.get(i));
            }
        }
        return targetList;
    }
}
