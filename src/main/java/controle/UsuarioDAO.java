package controle;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jasypt.util.password.BasicPasswordEncryptor;

import modelo.Usuario;

public class UsuarioDAO {
	
	private Session session;

	public UsuarioDAO(Session session) {
		this.session = session;
	}
	
	public Serializable salvar(Usuario usuario){
		
		if(validaNomeLogin(usuario.getLogin()))
			return this.session.save(usuario);
		else
			return -1;
	}
	
	public void excluir(Usuario usuario){
		this.session.delete(usuario);
	}
	
	public void atualizar(Usuario usuario){
		this.session.update(usuario);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuarios()
	{
		Query consulta = session.createQuery("from usuario");
		
		return consulta.list();
	}
	
	public Usuario getUsuario(Integer codigo)
	{
		Query consulta = session.createQuery("from usuario where codigo = :cod_param");
		consulta.setInteger("cod_param", codigo);
		
		return (Usuario) consulta.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getUsuLogins()
	{
		Query consulta = session.createQuery("select u.login from usuario u");
		
		return consulta.list();
	}
	
	public Boolean validaNomeLogin(String login)
	{
		List<String> logins = getUsuLogins();
		
		return !logins.contains(login);
	}
	
	public Usuario validaLogin(String login, String senha)
	{
//		Criteria criteria = session.createCriteria(Usuario.class, "u");
//		criteria.add(Restrictions.eq("u.login", login));
//		criteria.add(Restrictions.eq("u.senha", senha));
//		return (Usuario)criteria.uniqueResult();
		
		String hql = "select u from usuario u where u.login = :login_param";
		
		Query consulta = session.createQuery(hql);
		consulta.setString("login_param", login);
		
		Usuario u = (Usuario) consulta.uniqueResult();
		
		if(new BasicPasswordEncryptor().checkPassword(senha, u.getSenha()))
			return u;
		else
			return null;
	}

}
