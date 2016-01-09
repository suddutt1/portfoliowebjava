package com.ibm.tools.portfoliodb.data;

import com.ibm.tools.dbacess.DAOResponse;

public class BaseDAOResponse implements DAOResponse {

	private int status;
	private String statusText;
	private Object response;
	
	public BaseDAOResponse()
	{
		super();
	}

	@Override
	public int getStatus() {

		return status;
	}

	@Override
	public String getStatusText() {

		return statusText;
	}

	@Override
	public Object getResponse() {

		return response;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @param statusText the statusText to set
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Object response) {
		this.response = response;
	}

}
