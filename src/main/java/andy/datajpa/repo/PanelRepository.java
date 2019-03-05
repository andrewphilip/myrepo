package andy.datajpa.repo;

import org.springframework.data.repository.CrudRepository;

import andy.datajpa.models.Panel;

public interface PanelRepository extends CrudRepository<Panel, Long> {

	public Panel findByPanelCode(String panelCode);
}
