package komarov.avia.aviacompany.controller.manager;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import komarov.avia.aviacompany.entity.Flight;
import komarov.avia.aviacompany.entity.Goods;
import komarov.avia.aviacompany.entity.GoodsType;
import komarov.avia.aviacompany.service.FlightsService;
import komarov.avia.aviacompany.service.GoodTypesService;
import komarov.avia.aviacompany.service.GoodsService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/goods")
public class GoodsManagerController {
	
	private final GoodsService goodsService;
	
	private final FlightsService flightsService;
	
	private final GoodTypesService goodTypesService;
	
	@GetMapping("/all")
	public String getAllGoods(Model model) {
		List<Flight> flights = this.flightsService.findAll();
		List<GoodsType> goodsTypes = this.goodTypesService.findAll();
		List<Goods> goodsList = this.goodsService.findAll();
		
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("goodsTypes", goodsTypes);
		model.addAttribute("flights", flights);
		
		return "manager/goods";
	}
	
	@PostMapping("/add")
	public String createGood(@ModelAttribute Goods goods) {
		this.goodsService.create(goods);
		return "redirect:/manager/goods/all";
	}
	
	@PostMapping("/edit/{id}")
	public String updateGoods(@ModelAttribute Goods goods, @PathVariable int id) {
		this.goodsService.update(goods, id);
		return "redirect:/manager/goods/all";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteGoods(@PathVariable int id) {
		this.goodsService.delete(id);
		return "redirect:/manager/goods/all";
	}
	

}
