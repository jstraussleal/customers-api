package br.com.jstrauss.platformbuilders.customers.service;

import java.util.Calendar;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.jstrauss.platformbuilders.commons.exceptions.BusinessException;
import br.com.jstrauss.platformbuilders.commons.exceptions.TechnicalException;
import br.com.jstrauss.platformbuilders.commons.utils.DocumentCPF;
import br.com.jstrauss.platformbuilders.customers.model.customer.Customer;
import br.com.jstrauss.platformbuilders.customers.model.customer.CustomerRepository;
import br.com.jstrauss.platformbuilders.customers.payload.CustomerPayload;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	public CustomerPayload findCustomerById( Long customerId ) throws NotFoundException, BusinessException, TechnicalException {
		
		var entity = findById( customerId );
		
		var payload = castCustomerFromEntity( entity );
		
		return payload;
		
	}

	public CustomerPayload createCustomer( CustomerPayload customer ) throws IllegalArgumentException, BusinessException, TechnicalException {
		
		if ( customer.getId() != null ) {
			throw new IllegalArgumentException( "It is not allowed to enter id for creation." );
		}

		try {
			new DocumentCPF( customer.getCpf() );
		}
		catch ( IllegalArgumentException ex ) {
			throw new BusinessException( ex );
		}
		
		var newest = castCustomerFromPayload( customer );

		newest.setCreatedAt( Calendar.getInstance() );
		
		newest = customerRepo.save( newest );
		
		customer.setId( newest.getIdCustomer() );
		customer.setCriadoEm( newest.getCreatedAt() );
		
		return customer;
		
	}
	
	public Page< CustomerPayload > searchCustomer( CustomerPayload filters, int page, int size ) throws BusinessException, TechnicalException {
		
        var pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nmCustomer");
        
        var customerList = customerRepo.search( filters.getNome(), filters.getCpf(), pageRequest);
        
    	return customerList;
	
	}
	
	public CustomerPayload updateCustomer( CustomerPayload customer ) throws BusinessException, TechnicalException {
		
		try {
			new DocumentCPF( customer.getCpf() );
		}
		catch ( IllegalArgumentException ex ) {
			throw new BusinessException( ex );
		}
		
		var actual = findById( customer.getId() );
		
		var newest = castCustomerFromPayload( customer );

		newest.setCreatedAt( actual.getCreatedAt() );
		newest.setUpdatedAt( Calendar.getInstance() );
		
		newest = customerRepo.save( newest );
		
		customer.setCriadoEm( newest.getCreatedAt() );
		customer.setAtualizadoEm( newest.getUpdatedAt() );
		
		return customer;
		
	}

	public CustomerPayload applyChanges( CustomerPayload customer ) throws BusinessException, TechnicalException {
		
		var actual = findById( customer.getId() );
		
		if ( customer.getNome() != null ) {
			actual.setNmCustomer( customer.getNome() );
		}
		
		if ( customer.getCpf() != null ) {
			try {
				new DocumentCPF( customer.getCpf() );
			}
			catch ( IllegalArgumentException ex ) {
				throw new BusinessException( ex );
			}
			actual.setCdDocCpf( customer.getCpf() );
		}
		
		if ( customer.getDataNascimento() != null ) {
			actual.setDtBirth( customer.getDataNascimento() );
		}

		actual.setUpdatedAt( Calendar.getInstance() );
		
		actual = customerRepo.save( actual );
		
		customer.setCriadoEm( actual.getCreatedAt() );
		customer.setAtualizadoEm( actual.getUpdatedAt() );
		
		return customer;
		
	}
	
	public void removeCustomer( Long customerId ) throws BusinessException, TechnicalException {
		
		var customer = findById( customerId );
		
		customerRepo.deleteById( customer.getIdCustomer() );
		
	}
	
	private Customer findById( Long customerId ) throws NotFoundException, BusinessException, TechnicalException {
		
		var entity = customerRepo.findById( customerId );
		
		if ( !entity.isPresent() ) {
			throw new NotFoundException( "Customer " + customerId + " not found." );
		}
		
		return entity.get();
		
	}
	
	private Customer castCustomerFromPayload(CustomerPayload origin) {
	
		return new Customer()
				.idCustomer( origin.getId() )
				.nmCustomer( origin.getNome() )
				.cdDocCpf( origin.getCpf() )
				.dtBirth( origin.getDataNascimento() );
	
	}
	
	private CustomerPayload castCustomerFromEntity(Customer origin) {
		
		return new CustomerPayload()
				.id( origin.getIdCustomer() )
				.nome( origin.getNmCustomer() )
				.cpf( origin.getCdDocCpf() )
				.dataNascimento( origin.getDtBirth() )
				.criadoEm( origin.getCreatedAt() )
				.atualizadoEm( origin.getUpdatedAt() );
	
	}
	
}


/*
This is the only time you will be able to view this password. Copy and save the password for your reference, otherwise you will need to modify the database to change it. You can use a SQL client application or utility to connect to your database.
Learn about connecting to your database
Master username
postgres
Master password
platformbuilders+10Copy
Endpoint
platformbuilders2.cuhpisegsyw4.us-east-1.rds.amazonaws.comCopy
*/
