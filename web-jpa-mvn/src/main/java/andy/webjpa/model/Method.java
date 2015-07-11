package andy.webjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="methodinfo")
public class Method {

	@Id
	@Column(name="method")
	private String method;
	
	@Column(name="descr")
	private String descr;
	
	@Column(name="ratios")
	private String ratios;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getRatios() {
		return ratios;
	}

	public void setRatios(String ratios) {
		this.ratios = ratios;
	}
	
	
}
