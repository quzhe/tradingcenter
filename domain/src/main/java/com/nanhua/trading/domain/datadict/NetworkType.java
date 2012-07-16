package com.nanhua.trading.domain.datadict;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Query;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@DiscriminatorValue("network_type")
public class NetworkType extends DataDict {
	
	public static NetworkType findNetworkByName(String name){
		Query q = NetworkType.entityManager().createQuery("SELECT n FROM NetworkType AS n WHERE n.name= :name");
		q.setParameter("name", name);
        return (NetworkType)(q.getSingleResult());
	}
}
