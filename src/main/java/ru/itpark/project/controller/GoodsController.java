package ru.itpark.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itpark.project.service.GoodsService;

@Controller
@RequestMapping("/")
public class GoodsController {
    private final GoodsService service;

    public GoodsController(GoodsService service) {
        this.service = service;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("items", service.getAll());
        return "all";
    }
        @GetMapping("/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("item", service.getById(id));
        return "edit";
    }
    @GetMapping(value = "/search", params = "name") // Mapping - определяет то, что должно быть в запросе
    public String search(@RequestParam String name, Model model) {
        model.addAttribute("name", name); // чтобы отображать в поле поиска
        model.addAttribute("items", service.findByName(name));
        return "all";
    }


}
