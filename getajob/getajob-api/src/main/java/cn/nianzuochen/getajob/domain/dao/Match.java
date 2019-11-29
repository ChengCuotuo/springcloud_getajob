package cn.nianzuochen.getajob.domain.dao;

public class Match {
    private Integer id;

    private Integer pid;

    private Integer stuid;

    private String result;

    private String stp;

    private String pts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getStp() {
        return stp;
    }

    public void setStp(String stp) {
        this.stp = stp == null ? null : stp.trim();
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts == null ? null : pts.trim();
    }
}