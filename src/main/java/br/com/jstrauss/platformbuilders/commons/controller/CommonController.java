package br.com.jstrauss.platformbuilders.commons.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.jstrauss.platformbuilders.commons.exceptions.BusinessException;
import br.com.jstrauss.platformbuilders.commons.exceptions.TechnicalException;
import br.com.jstrauss.platformbuilders.commons.payload.ResponseErrorPayload;

public abstract class CommonController {

	@Autowired
	protected HttpServletRequest request;
	
	protected static final Logger console = LoggerFactory.getLogger( CommonController.class );

	@ExceptionHandler( BusinessException.class )
	public ResponseEntity<?> handleBusinessException( HttpServletRequest request, BusinessException ex ) {

		var response = new ResponseErrorPayload( HttpStatus.BAD_REQUEST, ex );
		console.warn( response.toString() );
		return new ResponseEntity<>(response, response.getError());
	
	}
	
	@ExceptionHandler( NotFoundException.class )
	public ResponseEntity<?> handleNotFoundException( HttpServletRequest request, NotFoundException ex ) {
	
		var response = new ResponseErrorPayload( HttpStatus.NOT_FOUND, ex );
		console.info( response.toString() );
		return new ResponseEntity<>(response, response.getError());
	
	}
	
	@ExceptionHandler( Exception.class )
	public ResponseEntity<?> handleException( HttpServletRequest request, Exception ex ) { 
		return handleTechnicalException( request, new TechnicalException( ex ) );
	}
	
	@ExceptionHandler( TechnicalException.class )
	public ResponseEntity<?> handleTechnicalException( HttpServletRequest request, TechnicalException ex ) { 
		
		var response = new ResponseErrorPayload( HttpStatus.INTERNAL_SERVER_ERROR, ex );
		console.error( response.toString() );
		return new ResponseEntity<>(response, response.getError());
		
	}
	
}