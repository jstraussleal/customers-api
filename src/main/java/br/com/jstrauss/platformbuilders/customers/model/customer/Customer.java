package br.com.jstrauss.platformbuilders.customers.model.customer;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.lang.Nullable;

@Entity(name = "Customer")
@Table(name = "customer", schema = "s_customers")
public class Customer implements Serializable{

    private static final long serialVersionUID = 9064081499806030286L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long idCustomer;

    @Column(name = "nm_customer")
    private String nmCustomer;

    @Column(name = "cd_doc_cpf")
    private String cdDocCpf;

    @Column(name = "dt_birth")
    private Date dtBirth;
    
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Calendar createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Nullable
    private Calendar updatedAt;

    public Customer() {
    }

    public Customer(Long idCustomer) {
    	this.idCustomer = idCustomer;
    }
    
    public Long getIdCustomer() {
        return this.idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNmCustomer() {
        return this.nmCustomer;
    }

    public void setNmCustomer(String nmCustomer) {
        this.nmCustomer = nmCustomer;
    }

    public String getCdDocCpf() {
        return this.cdDocCpf;
    }

    public void setCdDocCpf(String cdDocCpf) {
        this.cdDocCpf = cdDocCpf;
    }
    
    public Date getDtBirth() {
    	return this.dtBirth;
    }
    
    public void setDtBirth(Date dtBirth) {
    	this.dtBirth = dtBirth;
    }
    
    public Calendar getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Customer idCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
        return this;
    }

    public Customer nmCustomer(String nmCustomer) {
        this.nmCustomer = nmCustomer;
        return this;
    }

    public Customer cdDocCpf(String cdDocCpf) {
        this.cdDocCpf = cdDocCpf;
        return this;
    }

    public Customer dtBirth(Date dtBirth) {
    	this.dtBirth = dtBirth;
    	return this;
    }
    
    public Customer createdAt(Calendar createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Customer updatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(idCustomer, customer.idCustomer) 
        		&& Objects.equals(nmCustomer, customer.nmCustomer) 
        		&& Objects.equals(cdDocCpf, customer.cdDocCpf) 
        		&& Objects.equals(dtBirth, customer.dtBirth) 
        		&& Objects.equals(createdAt, customer.createdAt) 
        		&& Objects.equals(updatedAt, customer.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, nmCustomer, cdDocCpf, createdAt, updatedAt);
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
    
}