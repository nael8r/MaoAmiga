package controle;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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

	public List<Consulta> getConsultas(Date data)
	{

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		
		String dataString = cal.get(Calendar.YEAR) + "-";
		
		if (cal.get(Calendar.MONTH) < 10) {
			dataString +=  "0" + cal.get(Calendar.MONTH) + "-";
		} else {
			dataString +=  cal.get(Calendar.MONTH) + "-";
		}
		
		if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
			dataString +=  "0" + cal.get(Calendar.DAY_OF_MONTH);
		} else {
			dataString +=  cal.get(Calendar.DAY_OF_MONTH);
		}
		
		Query consulta = sessao.createQuery("from consulta where data = :cod_param");
		consulta.setString("cod_param", dataString);
		
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
