package visao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

import conexao.HibernateUtil;
import controle.ProdutosDAO;
import controle.UsuarioDAO;
import modelo.Produtos;
import modelo.Usuario;

public class Principal {
	
	
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
//		
		/*
		UsuarioDAO dao = new UsuarioDAO(session);
//		
//		Transaction transaction = session.beginTransaction();
//		
//		Usuario u = dao.getUsuario(1);
//		
//		u.toString();
//		
//		transaction.commit();
		dao.getUsuarios();
		session.close();
		*/
		
		ProdutosDAO produtosDAO = new ProdutosDAO(session);
		
		List<Produtos> produtos = produtosDAO.getProdutos();
		
		
		
		
		
		System.out.println(util.DateTimeFormat.formatDataParaBR(new Date()));
		System.out.println(new Date());
		
	}

}
