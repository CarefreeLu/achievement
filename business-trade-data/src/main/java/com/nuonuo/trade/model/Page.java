package com.nuonuo.trade.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 类描述：TODO
 *
 * @author Jianhui Lu
 * @date 2019/8/15 19:47
 */
@Data
@ToString
public class Page
{
    private static final int DEFAULT_CURRENT_PAGE = 1;

    private static final int DEFAULT_PAGE_SIZE = 8;
    /**
     * 当前页
     */
    @JsonProperty("current")
    private Integer currentPage;
    /**
     * 每页大小
     */
    @JsonProperty("pCount")
    private Integer pageSize;

    public int getCurrentPage()
    {
        return currentPage == null ? DEFAULT_CURRENT_PAGE : currentPage;
    }

    public int getPageSize()
    {
        return pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public static void main(String[] args)
    {
        Page page = new Page();
        page.setCurrentPage(2);
        page.setPageSize(5);
        String json = JSONObject.toJSONString(page);
        System.out.println(json);

        page = JSONObject.parseObject(json, Page.class);
        System.out.println(page);
    }
}
