package ru.itpark.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.project.dto.RentDto;
import ru.itpark.project.entity.GoodsEntity;
import ru.itpark.project.entity.GoodsType;
import ru.itpark.project.entity.RentEntity;
import ru.itpark.project.service.GoodsService;
import ru.itpark.project.util.RentUtil;

import java.time.LocalDateTime;

import static ru.itpark.project.util.RentUtil.calculateRentEndTime;
import static ru.itpark.project.util.RentUtil.localDateTimeToString;

@Controller
@RequestMapping("/")
public class GoodsController {
    private final GoodsService service;

    public GoodsController(GoodsService service) {
        this.service = service;
    }

    @GetMapping
    public String getAll() {
        return "index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        GoodsEntity goods = service.getById(id);
        model.addAttribute("goods", goods);
        // model.addAttribute("time", );
        return "edit";
    }

    @GetMapping(value = "/search", params = "name") // Mapping - определяет то, что должно быть в запросе
    public String search(@RequestParam String name, Model model) {
        model.addAttribute("name", name); // чтобы отображать в поле поиска
        model.addAttribute("items", service.findByName(name));
        return "all";
    }

    @GetMapping("/index/{type}")
    public String findByType(@PathVariable GoodsType type, Model model) {
        model.addAttribute("items", service.findByType(type));
        if (type.equals(GoodsType.BC)) {
            return "allBycicle";
        }
        if (type.equals(GoodsType.SK)) {
            return "allSkate";
        }
        throw new IllegalArgumentException("Unknown type");
    }

    //    @GetMapping(value = "/searchTime", params = "time") // Mapping - определяет то, что должно быть в запросе
//    public String searchTime(@RequestParam Integer time, Model model) {
//        model.addAttribute("item", time); // чтобы отображать в поле поиска
//        //model.addAttribute("items", service.findTimeRent(time));
//        return "edit";
    //}
//    @PostMapping("/rent")
    public String deadLineAndCalculateCost(@ModelAttribute RentDto rentDto, Model model) {
        Long time = rentDto.getTime();
        LocalDateTime localDateTime = calculateRentEndTime(time);
        String stringTime = localDateTimeToString(localDateTime);
        model.addAttribute("time", stringTime);
        Integer goodsId = rentDto.getGoodsId();
        GoodsEntity goodsEntity = service.getById(goodsId);
        double cost = goodsEntity.getPrice() * time / 60d;
        model.addAttribute("cost", cost);
        return "rent";
        //return "redirect:/" + rentDto.getGoodsId();
    }

    @PostMapping("/rent")
    public String doRent(@ModelAttribute RentDto rentDto) {
        RentEntity rentEntity = service.save(rentDto);
        return "redirect:/rent";
    }

    @GetMapping("/rent")
    public String getCurrentRent(Model model) {
        if (service.getCurrentRent() == null) {
            return "rent";
        }
        LocalDateTime localDateTime = service.getCurrentRent().getEndTime();
        String stringTime = localDateTimeToString(localDateTime);
        Long price = service.getCurrentRent().getGoods().getPrice();
        Long time = service.getCurrentRent().getTime();
        Double cost = price * time / 60d;
        model.addAttribute("time", stringTime);
        model.addAttribute("cost", cost);
        return "rent";

//    }
//
//    @PreAuthorize("hasAuthority('REMOVE')")
//    @PostMapping("/{id}/remove")
//    public String remove(@PathVariable int id) {
//        service.removeById(id);
//
//        return "redirect:/notes";
//    }
//
//    @PreAuthorize("@accountService.isOwned(#id)") // #id -> @PathVariable int id
//    @GetMapping("/{id}/owned")
//    public String preAuthorizeWithOurService(@PathVariable int id) {
//        return "pages/owned";
//    }
}}





