package com.nanhua.trading.domain.datadict;

import javax.persistence.DiscriminatorValue;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@DiscriminatorValue("network_type")
public class NetworkType extends DataDict {
}
