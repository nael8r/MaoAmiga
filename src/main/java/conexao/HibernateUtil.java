package conexao;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

/*
	Classe para início da conexão do Hiberate com o banco de dados
*/
public class HibernateUtil {
	// Sessão de comunicação
	private static final SessionFactory	sessionFactory	= buildSessionFactory();
	
	// Iniciando uma nova comunicação por meio da configuração existente no 'hibernate.cfg.xml'
 	private static SessionFactory buildSessionFactory() {
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");

			StandardServiceRegistryBuilder registradorServico = new StandardServiceRegistryBuilder();
			registradorServico.applySettings(cfg.getProperties());
			StandardServiceRegistry servico = registradorServico.build();

			return cfg.buildSessionFactory(servico);
	} catch (Throwable e) {
			System.out.println("Criação inicial do objeto SessionFactory falhou. Erro: " + e);
			throw new ExceptionInInitializerError(e);
		}
	}
	
	// Retorna a sessão
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
