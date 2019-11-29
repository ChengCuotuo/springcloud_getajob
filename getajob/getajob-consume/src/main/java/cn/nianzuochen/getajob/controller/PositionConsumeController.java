package cn.nianzuochen.getajob.controller;

import cn.nianzuochen.getajob.domain.dao.Position;
import cn.nianzuochen.getajob.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("position")
public class PositionConsumeController {

    @Autowired
    PositionService positionService;

    @GetMapping("/getpositionbyid/{id}")
    public Position loadPositionById(@PathVariable("id") Integer id) {
        return positionService.loadPositionById(id);
    }
}
