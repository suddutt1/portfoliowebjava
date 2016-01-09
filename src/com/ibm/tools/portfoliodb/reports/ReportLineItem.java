package com.ibm.tools.portfoliodb.reports;

import java.util.Map;

public class ReportLineItem {

	private String uomName;
	private int totalCount;
	
	private Map<String,String> rowLine;
	
	
	
	public ReportLineItem()
	{
		super();
	}
	/**
	 * @return the uomName
	 */
	public String getUomName() {
		return uomName;
	}
	/**
	 * @param uomName the uomName to set
	 */
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	/**
	 * @return the rowLine
	 */
	public Map<String, String> getRowLine() {
		return rowLine;
	}
	/**
	 * @param rowLine the rowLine to set
	 */
	public void setRowLine(Map<String, String> rowLine) {
		this.rowLine = rowLine;
	}
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
	
}
