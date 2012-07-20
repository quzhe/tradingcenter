package com.nanhua.trading.domain.audit;

import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class AdapterError extends AuditInfo{ 
	
	/**
	 * a json string like {username:xxx,password:xxx}
	 */
	@Size(max = 300)
    private String params;
	
	
	@Size(max = 1000)
    private String stackTrace;
	
	public static List<AdapterError> findAdapterErrorEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM AdapterError o order by o.date DESC", AdapterError.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
	
}
