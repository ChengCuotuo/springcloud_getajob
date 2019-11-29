package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.domain.dao.Position;
import cn.nianzuochen.getajob.vo.response.RespBean;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class PositionServiceFallBack implements FallbackFactory<PositionService> {
    @Override
    public PositionService create(Throwable throwable) {
        return new PositionService(){
            @Override
            public Position loadPositionById(Integer id) {
                Position position = new Position();
                position.setId(id);
                position.setName("延迟导致操作失败，请稍后重试！");
                return position;
            }
        };
    }
}
