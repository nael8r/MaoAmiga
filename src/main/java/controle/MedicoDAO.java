package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import modelo.Consulta;
import modelo.Medico;
import modelo.ReceituarioMedico;
import modelo.Usuario;

public class MedicoDAO {
	private Session session;
	
	public MedicoDAO() {
	}

	public MedicoDAO(Session session) {
		super();
		this.session = session;
	}
	
	public Serializable salvar(Medico medico)
	{
		return session.save(medico);
	}
	
	public void atualizar(Medico medico)
	{
		session.update(medico);
	}
	
	public void excluir(Medico medico)
	{
		session.delete(medico);
	}
	
	public List<Medico> getMedicos()
	{
		Query consulta = session.createQuery("from medico");
		
		return consulta.list();
	}
	
	public List<Medico> getMedicos(Integer limite)
	{
		Query consulta = session.createQuery("from medico");
		
		consulta.setMaxResults(limite);
		
		return consulta.list();
	}
	
	public Medico getMedico(Integer codigo)
	{
		Query consulta = session.createQuery("from medico where codigo = :cod_param");
		consulta.setInteger("cod_param", codigo);
		
		return (Medico) consulta.uniqueResult();
	}
	
	public List<Consulta> getConsultas(Integer codigo)
	{
		return getMedico(codigo).getConstultas();
	}
	
	public List<ReceituarioMedico> getReceituarios(Integer codigo)
	{
		List<Consulta> consultas = getConsultas(codigo);
		List<ReceituarioMedico> receituarios = new ArrayList<ReceituarioMedico>();
		
		for(Consulta consulta : consultas)
		{
			// TODO fazer ReceituarioMedicoDAO
		}
		
		return null;
	}
}
