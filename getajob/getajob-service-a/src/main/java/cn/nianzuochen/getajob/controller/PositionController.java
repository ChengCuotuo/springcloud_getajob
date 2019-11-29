package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.service.PositionService;
import cn.nianzuochen.getajob.domain.dao.Position;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api("职位控制器类")
@RestController
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/getpositionbyid/{id}")
    public Position loadPositionById(@PathVariable Integer id) {
        return positionService.selectByPrimaryKey(id);
    }
}
