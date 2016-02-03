package visao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

import conexao.HibernateUtil;
import controle.UsuarioDAO;
import modelo.Usuario;

public class Principal {
	
	
	
	public static void main(String[] args) {
		//Session session = HibernateUtil.getSessionFactory().openSession();
//		
//		UsuarioDAO dao = new UsuarioDAO(session);
//		
//		Transaction transaction = session.beginTransaction();
//		
//		Usuario u = dao.getUsuario(1);
//		
//		u.toString();
//		
//		transaction.commit();
		//session.close();
		
		
		
		System.out.println(util.DateTimeFormat.formatDataParaBR(new Date()));
		System.out.println(new Date());
		
	}

}
