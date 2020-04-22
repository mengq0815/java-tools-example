package com.example.tool.page;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 程序分页工具类
 */
public class PageUtils {

    /**
     * 默认第一页
     */
    private static final int PAGE = 1;

    /**
     * 默认一页10条
     */
    private static final int PAGE_SIZE = 10;


    public static Integer getStartRow(Integer pageNo, Integer pageSize) {
        if (null == pageNo) {
            pageNo = PAGE;
        }
        if (null == pageSize) {
            pageSize = PAGE_SIZE;
        }

        return pageSize * (pageNo - 1);
    }

    public static Integer getOffset(Integer pageSize) {
        if (null == pageSize) {
            pageSize = PAGE_SIZE;
        }
        return pageSize;
    }

    /**
     * list分页
     *
     * @param list
     * @param pageNo
     * @param pageSize
     */
    public static <T> List<T> listPage(List<T> list, Integer pageNo, Integer pageSize) {
        if (CollectionUtil.isEmpty(list)) {
            return list;
        }
        if (ObjectUtil.isNull(pageNo)) {
            pageNo = PAGE;
        }
        if (ObjectUtil.isNull(pageSize)) {
            pageSize = PAGE_SIZE;
        }
        int totalCount = list.size();
        pageNo = pageNo - 1;
        int fromIndex = pageNo * pageSize;
        if (fromIndex > totalCount) {
            return Lists.newArrayList();
        }
        int toIndex = ((pageNo + 1) * pageSize);
        if (toIndex > totalCount) {
            toIndex = totalCount;
        }
        return list.subList(fromIndex, toIndex);
    }

}