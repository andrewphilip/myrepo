package andy.datajpa.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
@Entity
public class Panel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String panelCode;
	private Date createdDate;
	
	
	public Panel() {
	}
	
	public Panel(String code, Date created) {
		this.panelCode=code;
		this.createdDate=created;
	}
	
	public Panel(String code) {
		this.panelCode=code;
	}
	
	public Long getId() {
		return id;
	}
	public String getPanelCode() {
		return panelCode;
	}
	public void setPanelCode(String panelCode) {
		this.panelCode = panelCode;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Panel [id=" + id + ", panelCode=" + panelCode + ", createdDate=" + createdDate + "]";
	}

	
	
	
}
