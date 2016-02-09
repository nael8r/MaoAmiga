package visao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import conexao.HibernateUtil;
import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;
import modelo.Consulta;

public class Principal {

	public static void main(String[] args) {
		
		Populator pop = new PopulatorBuilder().build();
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = sessao.beginTransaction();
		//sessao.save(pop.populateBean(Consulta.class));
		trans.commit();
		sessao.close();
		
	}

}
