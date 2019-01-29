package ru.itpark.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itpark.project.service.GoodsService;

@Controller
@RequestMapping("/")
public class GoodsController {
    private final GoodsService service;
    public GoodsController(GoodsService service) {
        this.service = service;
    }
    @GetMapping
    public String getAll(Model model){

    }


}
