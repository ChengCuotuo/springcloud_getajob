package cn.nianzuochen.getajob.vo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class BasicReq implements Serializable {
    private Integer start;

    private Integer size;
}
