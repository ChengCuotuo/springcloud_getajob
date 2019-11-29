package cn.nianzuochen.getajob.domain.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Enterprise {
    private Integer id;

    private String website;

    private String name;

    private String trademark;

    private String address;

    private String lables;

    private String des;

    private String lev;

    private Integer isDeleted;
}