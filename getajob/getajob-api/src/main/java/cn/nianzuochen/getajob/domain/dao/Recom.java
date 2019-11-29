package cn.nianzuochen.getajob.domain.dao;

public class Recom {
    private Integer id;

    private Integer stuid;

    private Integer tutorid;

    private String input;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public Integer getTutorid() {
        return tutorid;
    }

    public void setTutorid(Integer tutorid) {
        this.tutorid = tutorid;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input == null ? null : input.trim();
    }
}