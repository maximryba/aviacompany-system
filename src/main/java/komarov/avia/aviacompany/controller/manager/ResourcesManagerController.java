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
import komarov.avia.aviacompany.entity.Resource;
import komarov.avia.aviacompany.service.FlightsService;
import komarov.avia.aviacompany.service.ResourcesService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/resources")
public class ResourcesManagerController {

	private final ResourcesService resourcesService;
	
	private final FlightsService flightsService;
	
	@GetMapping("/all")
	public String getAll(Model model) {
		List<Resource> resources = this.resourcesService.findAll();
		List<Flight> flights = this.flightsService.findAll();
		model.addAttribute("flights", flights);
		model.addAttribute("resources", resources);
		return "manager/resources";
	}
	
	@PostMapping("/add")
	public String createResource(@ModelAttribute Resource resource) {
		this.resourcesService.create(resource);
		return "redirect:/manager/resources/all";
	}
	
	@PostMapping("/edit/{id}")
	public String updateResource(@ModelAttribute Resource resource, @PathVariable int id) {
		this.resourcesService.update(resource, id);
		return "redirect:/manager/resources/all";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteResource(@PathVariable int id) {
		this.resourcesService.delete(id);
		return "redirect:/manager/resources/all";
	}
	
}
