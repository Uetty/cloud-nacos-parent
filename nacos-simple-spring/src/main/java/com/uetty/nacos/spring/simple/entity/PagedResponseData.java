package com.uetty.nacos.spring.simple.entity;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

@Data
public class PagedResponseData<T> {

    private Long total;
    private Integer totalPage;
    private List<T> list;

    public PagedResponseData(Page<T> page) {
        this.total = page.getTotal();
        this.totalPage = page.getPages();
        this.list = page.getResult();
    }

}
