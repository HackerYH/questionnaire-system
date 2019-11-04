package com.chinameyer.questionnaire.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * author：HongYe
 */
@Data
public class PageTableRequest implements Serializable {

    private Integer offset;

    private Integer limit;

    private Integer page;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void countOffset(){
        if(null == this.page || null == this.limit){
            this.offset = 0;
            return;
        }
        this.offset = (this.page - 1) * limit;
    }
}
