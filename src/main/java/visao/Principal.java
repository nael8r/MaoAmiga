package visao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import conexao.HibernateUtil;

public class Principal {
	
	
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transaction = session.beginTransaction();
		transaction.commit();
		session.close();
	}

}
