package cn.com.onlinetool.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 带分页的公共返回对象
 * @author choice
 * @description:
 * @date 2018/10/21 0:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePageResult<T>{

    private int pageNo;

    private int pageSize;

    private long total;

    private int pages;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> pageData;


    public void setPageInfo(Page<T> page) {
        this.setPageNo(page.getPageNum());
        this.setPageSize(page.getPageSize());
        this.setPages(page.getPages());
        this.setTotal(page.getTotal());
        this.setPageData(page.getResult());
    }

}
