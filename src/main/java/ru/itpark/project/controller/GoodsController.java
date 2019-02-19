package ru.itpark.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.project.dto.RentDto;
import ru.itpark.project.entity.GoodsEntity;
import ru.itpark.project.entity.GoodsType;
import ru.itpark.project.entity.RentEntity;
import ru.itpark.project.service.GoodsService;
import java.time.LocalDateTime;
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
        Long price = goods.getPrice();
        RentEntity rentEntity=service.getCurrentRent(id);
        boolean hasCurrentRent= rentEntity !=null;

        model.addAttribute("hasCurrentRent",hasCurrentRent);
        model.addAttribute("price",price);
        model.addAttribute("goods", goods);
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
    @PostMapping("/rent")
    public String doRent(@ModelAttribute RentDto rentDto) {
        RentEntity rentEntity = service.save(rentDto);
        Integer id = rentEntity.getGoods().getId();
        return "redirect:/"+id+"/rent";
    }

    @GetMapping("/{goodsId}/rent")
    public String getCurrentRent(Model model,@PathVariable Integer goodsId) {
        if (service.getCurrentRent(goodsId) == null) {
            return "rent";
        }
        LocalDateTime localDateTime = service.getCurrentRent(goodsId).getEndTime();
        String stringTime = localDateTimeToString(localDateTime);
        Long price = service.getCurrentRent(goodsId).getGoods().getPrice();
        Long time = service.getCurrentRent(goodsId).getTime();

        Long cost = price * time;
        model.addAttribute("time", stringTime);
        model.addAttribute("cost", cost);
        model.addAttribute("goodsId",goodsId);
        return "rent";

    }
}





