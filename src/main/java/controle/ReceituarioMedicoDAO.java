package controle;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import modelo.ReceituarioMedico;

public class ReceituarioMedicoDAO {
	
	private Session sessao;

	public ReceituarioMedicoDAO(Session sessao) {
		super();
		this.sessao = sessao;
	}
	
	public Serializable salvar(ReceituarioMedico rm)
	{
		return sessao.save(rm);
	}
	
	public void atualiza(ReceituarioMedico rm)
	{
		sessao.update(rm);
	}
	
	public void excluir(ReceituarioMedico rm)
	{
		sessao.delete(rm);
	}
	
	public List<ReceituarioMedico> getReceituarios()
	{
		Query consulta = sessao.createQuery("from receituario_medico");
		
		return consulta.list();
	}
	
	public List<ReceituarioMedico> getReceituarios(Integer limite)
	{
		Query consulta = sessao.createQuery("from receituario_medico");
		consulta.setMaxResults(limite);
		
		return consulta.list();
	}
	
	public ReceituarioMedico getReceituario(Integer codigo)
	{
		Query consulta = sessao.createQuery("from receituario_medico where codigo = :cod_param");
		consulta.setInteger("cod_param", codigo);
		
		return (ReceituarioMedico)consulta.uniqueResult();
	}

}
