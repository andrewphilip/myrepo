package andy.datajpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PanelDAO {

	@Autowired
	EntityManager em;
	
	
	public List<String> getAllPanelCodesByCreatedDate(){
		System.out.println("Inside PanelDAO::getAllPanelCodesByCreatedDate()....");

		String query="Select p.panel_code from Panel p order by p.created_date desc";
		Query q= em.createNativeQuery(query);
		List<String> codes=q.getResultList();
		codes.forEach(System.out::println);
		return codes;
	}
}
