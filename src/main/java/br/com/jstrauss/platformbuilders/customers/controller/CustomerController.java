package br.com.jstrauss.platformbuilders.customers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jstrauss.platformbuilders.commons.controller.CommonController;
import br.com.jstrauss.platformbuilders.commons.exceptions.BusinessException;
import br.com.jstrauss.platformbuilders.commons.exceptions.TechnicalException;
import br.com.jstrauss.platformbuilders.customers.payload.CustomerPayload;
import br.com.jstrauss.platformbuilders.customers.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController extends CommonController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody public Page< CustomerPayload > getCustomerWithParameters
    			( @RequestParam(required=false) String nome
				, @RequestParam(required=false) String cpf
				, @RequestParam(required=false, defaultValue = "0") int page
				, @RequestParam(required=false, defaultValue = "10") int size
				) 
    		throws IllegalArgumentException, BusinessException, TechnicalException {
    	
    	var filters = new CustomerPayload()
    			.nome( ( nome != null ? nome.toLowerCase() : null ) )
    			.cpf( cpf );
    			
    	return customerService.searchCustomer(filters, page, size);
    	
    }
    
	@GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.OK )
    @ResponseBody public CustomerPayload getCustomer( @PathVariable Long customerId ) 
    		throws IllegalArgumentException, BusinessException, TechnicalException {
    	
    	return customerService.findCustomerById( customerId );
    	
    }

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody public CustomerPayload postCustomer( @RequestBody CustomerPayload customer ) 
    		throws BusinessException, TechnicalException {
    	
    	if ( customer.getId() != null ) {
    		throw new BusinessException( "Id cannot be entered." );
    	}
    	
    	return customerService.createCustomer( customer );
    	
    }
    
	@DeleteMapping(value = "/{customerId}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteCustomer( @PathVariable Long customerId ) 
    		throws IllegalArgumentException, BusinessException, TechnicalException {
    	
    	customerService.removeCustomer( customerId );
    	
    }
    
	@PatchMapping(value = "/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.NO_CONTENT )
    @ResponseBody public void patchCustomer( @PathVariable Long customerId, @RequestBody CustomerPayload customer ) 
    		throws IllegalArgumentException, BusinessException, TechnicalException {
    	
    	if ( customer.getId() != null && !customer.getId().equals( customerId ) ) {
    		throw new BusinessException( "Diverging Ids." );
    	}
    	
    	customer.setId( customerId );
    	
    	customerService.applyChanges( customer );
    	
    }
    
	@PutMapping(value = "/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.NO_CONTENT )
    @ResponseBody public void putCustomer( @PathVariable Long customerId, @RequestBody CustomerPayload customer ) 
    		throws IllegalArgumentException, BusinessException, TechnicalException {
    	
    	if ( customer.getId() != null && !customer.getId().equals( customerId ) ) {
    		throw new BusinessException( "Diverging Ids." );
    	}
    	
    	customer.setId( customerId );
    	
    	customerService.updateCustomer( customer );
    	
    }
    
}

