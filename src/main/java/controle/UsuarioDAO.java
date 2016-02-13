package controle;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.jasypt.util.password.BasicPasswordEncryptor;

import modelo.Medico;
import modelo.Usuario;

/*
	Servlet de controle de comunicação com o banco de dados da classe Usuário
*/
public class UsuarioDAO {
	
	private Session sessao;

	public UsuarioDAO(Session session) {
		this.sessao = session;
	}
	
	public Serializable salvar(Usuario usuario){
		
		if(validaNomeLogin(usuario.getLogin()))
		{
			Transaction trans = sessao.beginTransaction();
			Serializable cod = this.sessao.save(usuario);
			trans.commit();
			return cod;
		}
		else
			return -1;
	}
	
	public void excluir(Usuario usuario){
		Transaction trans = sessao.beginTransaction();
		this.sessao.delete(usuario);
		trans.commit();
	}
	
	public void atualizar(Usuario usuario){
		Transaction trans = sessao.beginTransaction();
		this.sessao.update(usuario);
		trans.commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuarios()
	{
		Query consulta = sessao.createQuery("from usuario");
		
		return consulta.list();
	}
	
	public Usuario getUsuario(Integer codigo)
	{
		Query consulta = sessao.createQuery("from usuario where codigo = :cod_param");
		consulta.setInteger("cod_param", codigo);
		
		return (Usuario) consulta.uniqueResult();
	}
	
	// Recolhe usuário que possui um nome que tenha 'nome' incluido
	public Usuario getUsuario(String nome)
	{
		Query consulta = sessao.createQuery("from usuario where nome like :id");
		consulta.setParameter("id", "%"+nome+"%");
		
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

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}
}
