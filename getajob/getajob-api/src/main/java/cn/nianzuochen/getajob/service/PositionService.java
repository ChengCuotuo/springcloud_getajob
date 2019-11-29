package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.domain.dao.Position;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "GETAJOBSERVICEA", fallbackFactory = PositionServiceFallBack.class)
public interface PositionService {
    @GetMapping("/getpositionbyid/{id}")
    public Position loadPositionById(@PathVariable("id")  Integer id);
}
