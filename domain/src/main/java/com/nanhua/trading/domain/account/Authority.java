package com.nanhua.trading.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="Authority")
public class Authority implements GrantedAuthority{
	public Authority(){
		
	}
	
	public Authority(String authority){
		this.authority = authority;
	}
	@Id     
	@GeneratedValue(strategy = GenerationType.TABLE)     
	@Column(name = "id", nullable = false)     
	private Integer id; 
	
	
	@NotNull
	@Size(max = 100)
	private String authority;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
