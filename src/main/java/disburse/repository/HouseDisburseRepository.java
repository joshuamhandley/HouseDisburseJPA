package disburse.repository;

import java.util.List;

import javax.persistence.Column;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import disburse.vo.HouseDisburseDetail;

public interface HouseDisburseRepository extends JpaRepository<HouseDisburseDetail, Integer> {
	
	
	public List<HouseDisburseDetail> findByBioGuideID(String bioGuideID);
	public List<HouseDisburseDetail> findByYear(String year);
	
	@Query(value = "SELECT h FROM HouseDisburseDetail h WHERE h.amount = (SELECT MAX(h.amount) FROM h)")
	public List<HouseDisburseDetail> findHighestAmount();
	
//	@Query(value = "INSERT INTO HouseDisburseDetail h VALUES(h.bioGuideID, h.office, h.category, h.payee, h.startDate, h.endDate, h.purpose, h.amount, h.year)")
//	public List<HouseDisburseDetail> insertDisbursesJPA(List<HouseDisburseDetail> hddList);
//	
	
		
}
