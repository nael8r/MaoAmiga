package controle;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import modelo.Consulta;
import modelo.Medico;
import modelo.ReceituarioExames;
import modelo.ReceituarioMedico;

public class ConsultaDAO {
	
	private Session sessao;

	public ConsultaDAO(Session sessao) {
		this.sessao = sessao;
	}
	
	public Serializable salvar(Consulta consulta)
	{
		return sessao.save(consulta);
	}
	
	public void atualizar(Consulta consulta)
	{
		sessao.update(consulta);
	}
	
	public void excluir(Consulta consulta)
	{
		sessao.delete(consulta);
	}
	
	public List<Consulta> getConsultas()
	{
		Query consulta = sessao.createQuery("from consulta");
		
		return consulta.list();
	}
	
	public List<Consulta> getConsultas(Integer limite)
	{
		Query consulta = sessao.createQuery("from consulta");
		
		consulta.setMaxResults(limite);
		
		return consulta.list();
	}
	
	public Consulta getConsulta(Integer codigo)
	{
		Query consulta = sessao.createQuery("from consulta where codigo = :cod_param");
		consulta.setInteger("cod_param", codigo);
		
		return (Consulta) consulta.uniqueResult();
	}
	
	public List<ReceituarioMedico> getReceituariosMedicos(Integer codigo)
	{
		try
		{
			return getConsulta(codigo).getReceituariosMedicos();
		}
		catch(NullPointerException e)
		{
			return null;
		}
		
	}
	
	public List<ReceituarioExames> getReceituariosExames(Integer codigo)
	{
		try
		{
			return getConsulta(codigo).getReceituariosExames();
		}
		catch(NullPointerException e)
		{
			return null;
		}
	}

}
