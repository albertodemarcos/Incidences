package es.myhome.portal.service.filters;

import java.io.Serializable;

public class FilterGoogleMarkerIncidence extends GlobalFilter implements IFilterHttpParam, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3990077325183879410L;
	
	private Double aNord;
	private Double aEst;
	private Double aSud;
	private Double aOvest;
	
	
	public FilterGoogleMarkerIncidence() {
		super();
	}
	
	public FilterGoogleMarkerIncidence(Double aNord, Double aEst, Double aSud, Double aOvest) {
		super();
		this.aNord = aNord;
		this.aEst = aEst;
		this.aSud = aSud;
		this.aOvest = aOvest;
	}

	public Double getaNord() {
		return aNord;
	}

	public void setaNord(Double aNord) {
		this.aNord = aNord;
	}

	public Double getaEst() {
		return aEst;
	}

	public void setaEst(Double aEst) {
		this.aEst = aEst;
	}

	public Double getaSud() {
		return aSud;
	}

	public void setaSud(Double aSud) {
		this.aSud = aSud;
	}

	public Double getaOvest() {
		return aOvest;
	}

	public void setaOvest(Double aOvest) {
		this.aOvest = aOvest;
	}

	@Override
	public String toString() {
		return "FilterGoogleMarkerIncidence [aNord=" + aNord + ", aEst=" + aEst + ", aSud=" + aSud + ", aOvest="
				+ aOvest + "]";
	}


}
