package com.ems.user.id.generator;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import ems.utility.util.EmsUtility;


public class AddressIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String prefix = "ADRS";
		String addressId = EmsUtility.createUniqueId(prefix);
		return addressId;
	}

}
