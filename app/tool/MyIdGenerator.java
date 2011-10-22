package tool;

import java.io.*;
import java.text.*;
import java.util.*;

import models.IGeneratedModel;

import org.hibernate.*;
import org.hibernate.engine.*;
import org.hibernate.id.*;

public class MyIdGenerator implements IdentifierGenerator {

	public Serializable generate(SessionImplementor session, Object arg1)
			throws HibernateException {
		if (arg1 instanceof IGeneratedModel) {
			IGeneratedModel generatedModel = (IGeneratedModel) arg1;
			if (generatedModel.getGeneratedValue() == null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyyMMddHHmmssSSS");
				Serializable id = dateFormat.format(new Date()) + "QQ";
				return id;
			} else {
				return generatedModel.getGeneratedValue();
			}
		}
		return null;
	}
}
