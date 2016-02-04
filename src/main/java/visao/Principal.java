package visao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

import conexao.HibernateUtil;
import controle.PacienteDAO;
import controle.UsuarioDAO;
import io.github.benas.jpopulator.api.Populator;
import io.github.benas.jpopulator.impl.PopulatorBuilder;
import modelo.Consulta;
import modelo.Medico;
import modelo.Paciente;
import modelo.Usuario;

public class Principal {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Populator pop = new PopulatorBuilder().build();
		
		List<Medico> medicos = new ArrayList<Medico>();
		
		Transaction transaction = session.beginTransaction();
		transaction.commit();
		
		List<Paciente> pacientes = new PacienteDAO(session).getPacientes();
		
		session.close();
//
//		System.out.println(util.DateTimeFormat.formatDataParaBR(new Date()));
//		System.out.println(new Date());

	}

}
