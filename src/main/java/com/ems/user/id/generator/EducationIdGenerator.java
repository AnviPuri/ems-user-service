package com.ems.user.id.generator;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.ems.user.utility.EmsUtility;

public class EducationIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String prefix = "EDUC";
		String educationId = EmsUtility.createUniqueId(prefix);
		return educationId;
	}

}
