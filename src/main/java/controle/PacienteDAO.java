package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import modelo.Consulta;
import modelo.Medico;
import modelo.Paciente;

public class PacienteDAO {
	
	private Session sessao;

	public PacienteDAO(Session sessao) {
		super();
		this.sessao = sessao;
	}
	
	public Serializable salvar(Paciente paciente)
	{
		return sessao.save(paciente);
	}
	
	public void atualiza(Paciente paciente)
	{
		sessao.update(paciente);
	}
	
	public void excluir(Paciente paciente)
	{
		sessao.delete(paciente);
	}
	
	public List<Paciente> getPacientes()
	{
		Query consulta = sessao.createQuery("from paciente");
		
		return consulta.list();
	}
	
	public List<Paciente> getPacientes(Integer limite)
	{
		Query consulta = sessao.createQuery("from paciente");
		consulta.setMaxResults(limite);
		
		return consulta.list();
	}
	
	public Paciente getPaciente(Integer codigo)
	{
		Query consulta = sessao.createQuery("from paciente where codigo = :cod_param");
		consulta.setInteger("cod_param", codigo);
		
		return (Paciente) consulta.uniqueResult();
		
	}
	
	public List<Consulta> getConsultas(Integer codigo)
	{
		try
		{
			return getPaciente(codigo).getConstultas();
		}
		catch(NullPointerException e)
		{
			return null;
		}
		
	}
	
	public List<Medico> getMedicos(Integer codigo)
	{
		List<Consulta> consultas = getConsultas(codigo);
		
		if(consultas == null)
			return null;
		
		List<Medico> medicos = new ArrayList<Medico>();
		
		for(Consulta consulta : consultas)
		{
			medicos.add(consulta.getMedico());
		}
		
		return medicos;
	}

}