package com.nanhua.trading.domain.audit;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table="AuditInfo",inheritanceType = "JOINED")
public class AuditInfo {
	
	@Size(max = 50)
    private String username;
	
	@NotNull
    @Temporal(TemporalType.DATE)
    private Date date;
	
	/**
	 * a json string like{desc:xxxxError,clientip:xxx,correlatorname:xxx}
	 */
	@NotNull
    private String message;
}
