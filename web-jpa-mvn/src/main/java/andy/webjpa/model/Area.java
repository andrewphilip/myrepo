package andy.webjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="area")
public class Area {

	@Id
	@Column(name="areaseq")
	private String areaseq;
	
	@Column(name="areacode")
	private String areacode;
	
	@Column(name="owner")
	private String owner;
	
	@Column(name="statenum")
	private String statenum;
	
	@Column(name="cityclaims")
	private String cityclaims;
	
	@Column(name="title")
	private String title;
	
	@Column(name="method")
	private String method;
	
	@Column(name="areatype")
	private String areatype;
	
	@Column(name="alttitle")
	private String altitle;
	
	public String getAreaseq() {
		return areaseq;
	}
	public void setAreaseq(String areaseq) {
		this.areaseq = areaseq;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getStatenum() {
		return statenum;
	}
	public void setStatenum(String statenum) {
		this.statenum = statenum;
	}
	public String getCityclaims() {
		return cityclaims;
	}
	public void setCityclaims(String cityclaims) {
		this.cityclaims = cityclaims;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getAreatype() {
		return areatype;
	}
	public void setAreatype(String areatype) {
		this.areatype = areatype;
	}
	public String getAltitle() {
		return altitle;
	}
	public void setAltitle(String altitle) {
		this.altitle = altitle;
	}
	
	
}
