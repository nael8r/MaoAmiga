package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import modelo.Consulta;
import modelo.Medico;
import modelo.Paciente;
import modelo.ReceituarioMedico;
import modelo.Usuario;

public class MedicoDAO {
	private Session sessao;
	
	public MedicoDAO() {
	}

	public MedicoDAO(Session session) {
		super();
		this.sessao = session;
	}
	
	public Serializable salvar(Medico medico)
	{
		return sessao.save(medico);
	}
	
	public void atualizar(Medico medico)
	{
		sessao.update(medico);
	}
	
	public void excluir(Medico medico)
	{
		sessao.delete(medico);
	}
	
	public List<Medico> getMedicos()
	{
		Query consulta = sessao.createQuery("from medico");
		
		return consulta.list();
	}
	
	public List<Medico> getMedicos(Integer limite)
	{
		Query consulta = sessao.createQuery("from medico");
		
		consulta.setMaxResults(limite);
		
		return consulta.list();
	}
	
	public Medico getMedico(Integer codigo)
	{
		Query consulta = sessao.createQuery("from medico where codigo = :cod_param");
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
	
	public List<Paciente> getPacientes(Integer codigo)
	{
		Query consulta = sessao.createQuery("from consulta where cod_medico = :cod_param");
		consulta.setInteger("cod_param", codigo);
		
		List<Consulta> consultas = consulta.list();
		List<Paciente> pacientes = new ArrayList<Paciente>();
		
		for(Consulta cons : consultas)
		{
			pacientes.add(cons.getPaciente());
		}
		
		if(pacientes.isEmpty())
			return null;
		
		return pacientes;
		
	}
}
