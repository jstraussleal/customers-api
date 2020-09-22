package br.com.jstrauss.platformbuilders.commons.payload;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

public class ResponseErrorPayload implements Serializable{

	private static final long serialVersionUID = -7245567171185829024L;
	
	private int status			= HttpStatus.INTERNAL_SERVER_ERROR.value();
	private HttpStatus error 	= HttpStatus.INTERNAL_SERVER_ERROR;
	private String userMessage 	= "Ocorreu um erro inesperado. Tente novamente, por favor.";
	private String techMessage 	= "";
	
	public ResponseErrorPayload( HttpStatus status ) {
		this( status, "" );
	}

	public ResponseErrorPayload( HttpStatus status, String userMessage ) {
		this.error = status;
		this.status = status.value();
		this.userMessage = userMessage;
	}
	
	public ResponseErrorPayload( HttpStatus error, Exception ex ) {
		this(error, ex.getMessage());
		this.techMessage = ex.getMessage();
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public HttpStatus getError() {
		return error;
	}
	public void setError(HttpStatus error) {
		this.error = error;
	}
	
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	
	public String getTechMessage() {
		return techMessage;
	}
	public void setTechMessage(String techMessage) {
		this.techMessage = techMessage;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
}
