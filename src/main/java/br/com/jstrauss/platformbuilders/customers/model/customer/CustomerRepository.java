package br.com.jstrauss.platformbuilders.customers.model.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jstrauss.platformbuilders.customers.payload.CustomerPayload;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    @Query("SELECT new CustomerPayload( idCustomer, nmCustomer, cdDocCpf, dtBirth, createdAt, updatedAt ) "
    		+ "FROM Customer c "
    		+ "WHERE (:nmCustomer IS NULL OR LOWER(c.nmCustomer) LIKE %:nmCustomer%) " 
    		+ "AND (:cdDocCpf IS NULL OR c.cdDocCpf = :cdDocCpf) ")
    Page< CustomerPayload > search( @Param("nmCustomer") String nmCustomer, @Param("cdDocCpf") String cdDocCpf, Pageable pageable );
    
}
