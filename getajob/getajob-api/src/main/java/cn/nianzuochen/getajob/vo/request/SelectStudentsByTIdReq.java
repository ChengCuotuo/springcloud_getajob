package cn.nianzuochen.getajob.vo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SelectStudentsByTIdReq extends BasicReq {
    private Integer tId;
}
