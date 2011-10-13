package tool;

import java.io.*;
import java.text.*;
import java.util.*;

import org.hibernate.*;
import org.hibernate.engine.*;
import org.hibernate.id.*;

public class MyIdGenerator implements IdentifierGenerator {  
  
    public Serializable generate( SessionImplementor session, Object arg1 )  
            throws HibernateException {  
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Serializable id = dateFormat.format(new Date())+"QQ";;  
        return id;  
    }  
}  
