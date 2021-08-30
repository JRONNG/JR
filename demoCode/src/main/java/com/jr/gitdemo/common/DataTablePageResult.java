
package com.jr.gitdemo.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DataTablePageResult<T> {
    /**
     * data:payload.
     */
    private List<T> data;
    /**
     * offset:页数下标.
     */
    private Long offset;
    /**
     * limit:每页的数据行数.
     */
    private Long limit;
    /**
     * total:总记录数.
     */
    private Long total;

    /**
     * filtered:过滤之后的数据
     */
    private Long filtered;

    public DataTablePageResult() {
        super();
    }

    public DataTablePageResult(List<T> data, Long offset, Long limit, Long total) {
        super();
        this.data = data;
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.total = total;
    }

    public DataTablePageResult(List<T> data, Long offset, Long limit, Long total, Long filtered) {
        super();
        this.data = data;
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.filtered = filtered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }


    public Long getFiltered() {
        return filtered;
    }

    public void setFiltered(Long filtered) {
        this.filtered = filtered;
    }

    public Map<String, Object> getResult() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("draw", UUID.randomUUID().toString());
        param.put("recordsTotal", total);
        param.put("recordsFiltered", Long.valueOf(0l).compareTo(filtered) == 0 ? total : filtered);
        param.put("data", data);
        return param;
    }
}
