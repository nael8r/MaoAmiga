package controle;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import modelo.ReceituarioExames;

public class ReceituarioExamesDAO {
	
	private Session sessao;

	public ReceituarioExamesDAO(Session sessao) {
		super();
		this.sessao = sessao;
	}
	
	public Serializable salvar(ReceituarioExames re)
	{
		return sessao.save(re);
	}
	
	public void atualizar(ReceituarioExames re)
	{
		sessao.update(re);
	}
	
	public void excluir(ReceituarioExames re)
	{
		sessao.delete(re);
	}
	
	public List<ReceituarioExames> getReceituarios()
	{
		Query consulta = sessao.createQuery("from receituario_exames");
		
		return consulta.list();
	}
	
	public List<ReceituarioExames> getReceituarios(Integer limite)
	{
		Query consulta = sessao.createQuery("from receituario_exames");
		consulta.setMaxResults(limite);
		
		return consulta.list();
	}
	
	public ReceituarioExames getReceituario(Integer codigo)
	{
		Query consulta = sessao.createQuery("from receituario_exames where codigo = cod_param");
		consulta.setInteger("cod_param", codigo);
		
		return (ReceituarioExames)consulta.uniqueResult();
	}

}
