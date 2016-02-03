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
	
	private Session sessao;

	public UsuarioDAO(Session session) {
		this.sessao = session;
	}
	
	public Serializable salvar(Usuario usuario){
		
		if(validaNomeLogin(usuario.getLogin()))
			return this.sessao.save(usuario);
		else
			return -1;
	}
	
	public void excluir(Usuario usuario){
		this.sessao.delete(usuario);
	}
	
	public void atualizar(Usuario usuario){
		this.sessao.update(usuario);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuarios()
	{
		Query consulta = sessao.createQuery("from usuario");
		
		return consulta.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuarios(Integer limite)
	{
		Query consulta = sessao.createQuery("from usuario");
		consulta.setMaxResults(limite);
		
		return consulta.list();
	}
	
	public Usuario getUsuario(Integer codigo)
	{
		Query consulta = sessao.createQuery("from usuario where codigo = :cod_param");
		consulta.setInteger("cod_param", codigo);
		
		return (Usuario) consulta.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getUsuLogins()
	{
		Query consulta = sessao.createQuery("select u.login from usuario u");
		
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
		
		Query consulta = sessao.createQuery(hql);
		consulta.setString("login_param", login);
		
		Usuario u = (Usuario) consulta.uniqueResult();
		
		//if(new BasicPasswordEncryptor().checkPassword(senha, u.getSenha()))
		if(u != null && senha.equals(u.getSenha()))
			return u;
		else
			return null;
	}

}
