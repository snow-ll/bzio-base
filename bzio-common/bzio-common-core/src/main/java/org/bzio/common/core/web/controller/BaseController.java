package org.bzio.common.core.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.bzio.common.core.util.ServletUtil;
import org.bzio.common.core.web.entity.TableData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * web层通用数据处理
 *
 * @author: snow
 */
public class BaseController {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 分页
     */
    protected void startPage() {
        int pageNum = Integer.parseInt((ServletUtil.getParameter("pageNum")));
        int pageSize = Integer.parseInt((ServletUtil.getParameter("pageSize")));
        String orderBy = ServletUtil.getParameter("orderBy");
        log.info("分页参数： pageNum={}, pageSize={}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize, orderBy);
    }

    /**
     * 封装列表数据
     *
     * @param list 封装集合数据
     * @return TableData列表数据
     */
    protected TableData getTableData(List<?> list) {
        TableData rspData = new TableData();
        rspData.setCode(HttpStatus.OK.value());
        rspData.setRows(list);
        rspData.setMsg("查询成功");
        rspData.setTotal(new PageInfo<>(list).getTotal());
        return rspData;
    }
}
