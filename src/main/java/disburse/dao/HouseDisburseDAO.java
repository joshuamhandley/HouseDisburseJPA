package disburse.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import disburse.repository.HouseDisburseRepository;
import disburse.vo.HouseDisburseDetail;

@Component
public class HouseDisburseDAO {
	private HouseDisburseRepository hdRepository;

	@Autowired
	void setHdRepository(HouseDisburseRepository hdRepository) {
		this.hdRepository = hdRepository;
	}
	
	HouseDisburseRepository getHdRepository() {
		return hdRepository;
	}

	
	public List<HouseDisburseDetail> getAllDisbursesJPA() {
		List<HouseDisburseDetail> hddList = hdRepository.findAll();
		for (HouseDisburseDetail hdd : hddList) {
			hdd.setOffice(hdd.getOffice().toLowerCase());
		}
		return hddList;
	}
	
	public List<HouseDisburseDetail> getAllDisbursesByBioGuideIDJPA(String bioGuideID) {
		return hdRepository.findByBioGuideID(bioGuideID);
	}
	
	
	public List<HouseDisburseDetail> insertDisbursesJPA(List<HouseDisburseDetail> reqList, String year) { 
		hdRepository.saveAll(reqList);
		return hdRepository.findByYear(year);
	}
	
	
	public List<HouseDisburseDetail> getAllAndChangeCategoriesJPA() {
		List<HouseDisburseDetail> hddList = hdRepository.findAll();
		
		for (HouseDisburseDetail hdd : hddList) {
			if (hdd.getCategory() != null) {
				
				switch (hdd.getCategory()) {
				case "TRAVEL":
					hdd.setCategory("T");
					break;
				case "FRANKED MAIL":
					hdd.setCategory("FM");
					break;
				case "PERSONNEL COMPENSATION":
					hdd.setCategory("PC");
					break;
				case "RENT, COMMUNICATION, UTILITIES":
					hdd.setCategory("R");
					break;
				case "EQUIPMENT":
					hdd.setCategory("E");
					break;
				case "OTHER SERVICES":
					hdd.setCategory("OS");
					break;
				case "SUPPLIES AND MATERIALS":
					hdd.setCategory("SM");
					break;
				case "PRINTING AND REPRODUCTION":
					hdd.setCategory("PR");
					break;
				default:
					break;
				}
			}
		}
		return hdRepository.saveAll(hddList);
	}
	
	
	public List<HouseDisburseDetail> getHighestAmountJPA() {
		return hdRepository.findHighestAmount();
	}
}
