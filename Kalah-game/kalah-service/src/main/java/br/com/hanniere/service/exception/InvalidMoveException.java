package br.com.hanniere.service.exception;

public class InvalidMoveException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public InvalidMoveException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


}
