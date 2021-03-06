package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Consulta;
import modelo.Medico;
import modelo.Paciente;

/*
	Servlet de controle de comunicação com o banco de dados da classe Paciente
*/
public class PacienteDAO {
	
	private Session sessao;

	public PacienteDAO(Session sessao) {
		super();
		this.sessao = sessao;
	}
	
	public Serializable salvar(Paciente paciente)
	{
		Transaction trans = sessao.beginTransaction();
		Serializable cod = sessao.save(paciente);
		trans.commit();
		return cod;
	}
	
	public void atualiza(Paciente paciente)
	{
		Transaction trans = sessao.beginTransaction();
		sessao.update(paciente);
		trans.commit();
	}
	
	public void excluir(Paciente paciente)
	{
		Transaction trans = sessao.beginTransaction();
		sessao.delete(paciente);
		trans.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Paciente> getPacientes()
	{
		Query consulta = sessao.createQuery("from paciente");
		
		return consulta.list();
	}

	@SuppressWarnings("unchecked")
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
	
	// Pega pacientes que tiver 'nome' incluido no nome
	public Paciente getPaciente(String nome)
	{
		// https://stackoverflow.com/questions/28555942/hibernate-query-for-searching-part-of-string
		Query consulta = sessao.createQuery("from paciente where nome like :id");
		consulta.setParameter("id", "%"+nome+"%");
		
		return (Paciente) consulta.uniqueResult();
	}
	
	
	// Pega todos os pacientes que tiver 'nome' incluido no nome
	public List<Paciente> getPacientes(String nome)
	{
		// https://stackoverflow.com/questions/28555942/hibernate-query-for-searching-part-of-string
		Query consulta = sessao.createQuery("from paciente where nome like :id");
		consulta.setParameter("id", "%"+nome+"%");
		
		return consulta.list();
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

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}
	
	

}
