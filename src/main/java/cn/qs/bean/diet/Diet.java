package cn.qs.bean.diet;

import java.util.Date;

public class Diet {
    private Integer id;

    private String datasource;

    private Date createtime;

    private String crowurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource == null ? null : datasource.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCrowurl() {
        return crowurl;
    }

    public void setCrowurl(String crowurl) {
        this.crowurl = crowurl == null ? null : crowurl.trim();
    }
}