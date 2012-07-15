package com.nanhua.trading.domain.account;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;



@RooJavaBean(gettersByDefault=false)
@RooToString
@RooJpaActiveRecord(table="User",inheritanceType = "JOINED")
public class User implements UserDetails{
	
	public User(){
		
	}
	
    @NotNull
    @Column(unique = true)
    @Size(max = 50)
    private String username;

    @NotNull
    @Size(max = 100)
    //@JsonIgnore
    private String password;

    @Size(max = 100)
    @Transient
    private String confirmPassword;
    
    
    @NotNull
    @Column(unique = true)
    @Size(max = 50)
    @Pattern(regexp = "^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$")
	private String email;
    
    
    public String getEmail() {
		return email;
	}

	@NotNull
    private Boolean enabled = Boolean.TRUE;
    
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<Authority> authorities = new HashSet<Authority>();
    
    
    
    @JsonIgnore
    public Set<Authority> getAuthoritiesBySet() {
		return authorities;
    }
    
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> result = new HashSet<GrantedAuthority>();
		result.addAll(authorities);
		return result;
	}
    
	//@JsonIgnore
    @Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void autoGeneratePassword(){
		this.setPassword(genAutoPassword());
	}
	
	public static String genAutoPassword() {
        Random r = new Random();  
        String pw = "";  
        for(int i=0; i<8; i++){  
           pw += Integer.toString(r.nextInt(36), 36);  
        }
        return pw;
	}
	
	public static User findUserByUniqueIdentify(String identify){
		
		Query q = entityManager().createQuery("SELECT  s FROM User AS s WHERE s.username= :identify or s.email=:identify");
        q.setParameter("identify", identify);
        
        return (User)(q.getSingleResult());
    
	}
	public static void changePasswd(String oldpw,String newpw){
		UserDetails userdetail = (UserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		User u = User.findUserByUniqueIdentify(userdetail.getUsername());
		if(u.getPassword().equals(oldpw)){
			u.setPassword(newpw);
			u.merge();
		}else{
			new BadCredentialsException("");
		}
	}
	
}
