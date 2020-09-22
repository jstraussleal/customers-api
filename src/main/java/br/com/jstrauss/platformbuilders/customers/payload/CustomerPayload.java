package br.com.jstrauss.platformbuilders.customers.payload;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity(name = "CustomerPayload")
public class CustomerPayload implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_customer")
    private Long id;
	
	@Column(name = "nm_customer")
    private String nome;
	
	@Column(name = "cd_doc_cpf")
    private String cpf;
	
	@Column(name = "dt_birth")
    private Date dataNascimento;
	
    private Integer idade;
    
    @Column(name = "created_at")
    private Calendar criadoEm;
    
    @Column(name = "updated_at")
    private Calendar atualizadoEm;

    public CustomerPayload() {
    }
    
    public CustomerPayload( Long id, String nome, String cpf, Date dataNascimento, Calendar criadoEm, Calendar atualizadoEm ) {
    	this.id = id;
    	this.nome = nome;
    	this.cpf = cpf;
    	this.dataNascimento = dataNascimento;
    	this.criadoEm = criadoEm;
    	this.atualizadoEm = atualizadoEm;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void seNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public Date getDataNascimento() {
    	return this.dataNascimento;
    }
    
    public void setDataNascimento(Date dataNascimento) {
    	this.dataNascimento = dataNascimento;
    }
    
    public Integer getIdade() {
    	if ( this.idade == null ) {
    		var dtNascLocalDate = dataNascimento.toInstant()
    			      .atZone(ZoneId.systemDefault())
    			      .toLocalDate();
    					
			this.idade = Period.between( dtNascLocalDate, LocalDate.now() ).getYears();
    	}
    	return this.idade;
    }
    
    public Calendar getCriadoEm() {
        return this.criadoEm;
    }

    public void setCriadoEm(Calendar criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Calendar getAtualizadoEm() {
        return this.atualizadoEm;
    }

    public void setAtualizadoEm(Calendar atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }

    public CustomerPayload id(Long id) {
        this.id = id;
        return this;
    }

    public CustomerPayload nome(String nome) {
        this.nome = nome;
        return this;
    }

    public CustomerPayload cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public CustomerPayload dataNascimento(Date dataNascimento) {
    	this.dataNascimento = dataNascimento;
    	return this;
    }

    public CustomerPayload criadoEm(Calendar criadoEm) {
        this.criadoEm = criadoEm;
        return this;
    }

    public CustomerPayload atualizadoEm(Calendar atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CustomerPayload)) {
            return false;
        }
        CustomerPayload customer = (CustomerPayload) o;
        return Objects.equals(id, customer.id) 
        		&& Objects.equals(nome, customer.nome) 
        		&& Objects.equals(cpf, customer.cpf) 
        		&& Objects.equals(dataNascimento, customer.dataNascimento)
        		&& Objects.equals(idade, customer.idade) 
        		&& Objects.equals(criadoEm, customer.criadoEm) 
        		&& Objects.equals(atualizadoEm, customer.atualizadoEm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, dataNascimento, idade, criadoEm, atualizadoEm);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
    
}