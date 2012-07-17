package com.nanhua.trading.domain.customer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Query;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.nanhua.trading.domain.account.Authority;
import com.nanhua.trading.domain.account.User;
import com.nanhua.trading.domain.correlator.Correlator;
import com.nanhua.trading.domain.correlator.ServiceInstance;
import com.nanhua.trading.domain.datadict.NetworkType;
import com.nanhua.trading.domain.servicesupplier.ServiceSupplier;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@JsonIgnoreProperties(ignoreUnknown = true)
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Customer extends User {

	@NotNull
	private String address;

	@NotNull
	private String name;

	@NotNull
	private String mobile;

	@NotNull
	private String phone;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "correlator_id"), uniqueConstraints = { @UniqueConstraint(columnNames = {
			"customer_id", "correlator_id" }) })
	private Set<Correlator> correlators = new HashSet<Correlator>();

	// @OneToMany(cascade =
	// CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval=true)

	@Override
	public void remove() {
		Set<Correlator> sis = this.getCorrelators();

		for (Correlator si : sis) {
			sis.remove(si);
		}

		if (entityManager().contains(this)) {
			entityManager().remove(this);
		} else {
			User attached = User.findUser(this.getId());
			entityManager().remove(attached);
		}
	}

	public static Customer findCustomerByUniqueIdentify(String identify) {
		Query q = entityManager()
				.createQuery(
						"SELECT  s FROM Customer AS s WHERE s.username= :identify or s.email=:identify");
		q.setParameter("identify", identify);
		return (Customer) (q.getSingleResult());

	}
	
	//TODO a simple method for generate test user name, we dont need to consider concurrent
	public static Customer createTester() {
		Query q = entityManager()
				.createQuery(
						"SELECT  s FROM Customer AS s WHERE s.username LIKE :name ORDER BY s.username DESC");
		q.setParameter("name", "test%");
		List<Customer> list = q.getResultList();
		Integer i = 1;
		for (Customer c : list) {
			if(StringUtils.isNumeric(c.getUsername().substring(4))){
				i = Integer.valueOf(c.getUsername().substring(4));
				i = i+1;
				break;
			}
		}
		
		
		Customer test = new Customer();
		test.setUsername("test"+i);
		test.setPassword("password");
		test.setEnabled(true);
		test.setEmail(test.getUsername()+"@nawaa.com");
		test.getAuthoritiesBySet().add(new Authority("ROLE_CUSTOMER"));
		test.setAddress("地址");
		test.setName("测试用户"+test.getUsername());
		test.setMobile("111111111");
		test.setPhone("111111");
		Correlator cor = new Correlator();
		cor.setName("服务器1");
		cor.setNetworkType(NetworkType.findNetworkByName("联通"));
		cor.setHostname("127.0.0.1");
		cor.setPort("55555");
		ServiceInstance si = new ServiceInstance();
		si.setServiceid("ctp_trade_test");
		si.setSupplier(ServiceSupplier.findSupplierByPrefix("ctp_trade_test"));
		si.setCorrelator(cor);
		cor.getServiceInstances().add(si);
		
		si = new ServiceInstance();
		si.setServiceid("ctp_md_test");
		si.setSupplier(ServiceSupplier.findSupplierByPrefix("ctp_md_test"));
		si.setCorrelator(cor);
		cor.getServiceInstances().add(si);
		
		si = new ServiceInstance();
		si.setServiceid("ks_trade_test");
		si.setSupplier(ServiceSupplier.findSupplierByPrefix("ks_trade_test"));
		si.setCorrelator(cor);
		cor.getServiceInstances().add(si);
		
		si = new ServiceInstance();
		si.setServiceid("ks_md_test");
		si.setSupplier(ServiceSupplier.findSupplierByPrefix("ks_md_test"));
		si.setCorrelator(cor);
		cor.getServiceInstances().add(si);
		
		test.getCorrelators().add(cor);
		try{
		test.persist();
		}catch(Exception e){
			e.printStackTrace();
		}
		return test;
	}
	
}
