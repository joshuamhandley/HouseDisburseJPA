package disburse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import disburse.dao.HouseDisburseDAO;
import disburse.vo.HouseDisburseDetail;

@Controller
public class HouseDisburseController {
	
	@Autowired
	private HouseDisburseDAO dao;

	
	@GetMapping(value = "/all")
	public String getAllRecords(Model model) {
		model.addAttribute("hdList", dao.getAllDisbursesJPA());
		return "disburse";
	}
	
	
	@GetMapping(value = "/bioGuideID/{bioGuideID}")
	public String getRecordsOfBioGuideID(@PathVariable String bioGuideID, Model model) {
		model.addAttribute("hdList", dao.getAllDisbursesByBioGuideIDJPA(bioGuideID));
		return "disburse";
	}
	
	
	@PostMapping(value = "/loadDisburse")
	public @ResponseBody List<HouseDisburseDetail> loadDisburse(@RequestBody final List<HouseDisburseDetail> reqList) {
		return dao.insertDisbursesJPA(reqList, "2020");
	}
	
	
	@GetMapping(value = "/changeCategory")
	public String changeCategories(Model model) {
		model.addAttribute("hdList", dao.getAllAndChangeCategoriesJPA());
		return "disburse";
	}
	
	@GetMapping(value = "highestAmount")
	public String getHighestAmount(Model model) {
		model.addAttribute("hdList", dao.getHighestAmountJPA());
		return "disburse";
	}
	
	

}
