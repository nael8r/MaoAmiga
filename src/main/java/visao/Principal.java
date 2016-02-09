package visao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import conexao.HibernateUtil;

public class Principal {

	public static void main(String[] args) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = sessao.beginTransaction();
		
		trans.commit();
		sessao.close();
		
	}

}
