package br.com.hanniere.service.exception;

public class InvalidRuleException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public InvalidRuleException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




}
