package komarov.avia.aviacompany.controller.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import komarov.avia.aviacompany.entity.GoodsType;
import komarov.avia.aviacompany.service.GoodTypesService;

import java.util.List;

@Controller
@RequestMapping("/manager/goodTypes")
@RequiredArgsConstructor
public class GoodTypesManagerController {

    private final GoodTypesService goodTypesService;

    @GetMapping("/all")
    public String getAllGoodTypes(Model model) {
        List<GoodsType> goodsTypes = this.goodTypesService.findAll();
        model.addAttribute("goodsTypes", goodsTypes);
        return "manager/goodTypes";
    }
    
    @PostMapping("/add")
    public String addGoodType(@ModelAttribute GoodsType goodsType) {
    	this.goodTypesService.create(goodsType);
    	return "redirect:/manager/goodTypes/all";
    }
    
    @PostMapping("/edit/{id}")
    public String addGoodType(@ModelAttribute GoodsType goodsType, @PathVariable int id) {
    	this.goodTypesService.update(goodsType, id);
    	return "redirect:/manager/goodTypes/all";
    }
    
    @PostMapping("/delete/{id}")
    public String addGoodType(@PathVariable int id) {
    	this.goodTypesService.delete(id);
    	return "redirect:/manager/goodTypes/all";
    }

}
