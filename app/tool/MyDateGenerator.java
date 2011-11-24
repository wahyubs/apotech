package tool;

import java.io.Serializable;
import java.util.Date;

import models.IGeneratedModel;
import models.IGeneratedTransaction;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class MyDateGenerator implements IdentifierGenerator {

	public Serializable generate(SessionImplementor session, Object arg1)
			throws HibernateException {
		if (arg1 instanceof IGeneratedTransaction) {
			IGeneratedTransaction generatedModel = (IGeneratedTransaction) arg1;
			if (generatedModel.getGeneratedDate() == null) {
				return new Date();
			} else {
				return generatedModel.getGeneratedDate();
			}
		}
		return null;
	}
}
