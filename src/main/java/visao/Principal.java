package visao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

import conexao.HibernateUtil;
import controle.UsuarioDAO;
import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;
import modelo.Consulta;
import modelo.Medico;
import modelo.Usuario;

public class Principal {

	public static void main(String[] args) {
		//Session session = HibernateUtil.getSessionFactory().openSession();
		
		Populator pop = new PopulatorBuilder().build();
		
		Medico medico = (Medico)pop.populateBean(Medico.class);
		Consulta consulta = (Consulta)pop.populateBean(Consulta.class);
//		
//		Transaction transaction = session.beginTransaction();
//		transaction.commit();
//		
//		
//		session.close();
//
//		System.out.println(util.DateTimeFormat.formatDataParaBR(new Date()));
//		System.out.println(new Date());

	}

}
