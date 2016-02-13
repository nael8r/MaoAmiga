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
import modelo.ReceituarioMedico;
import modelo.Usuario;

/*
	Servlet de controle de comunicação com o banco de dados da classe Médico
*/
public class MedicoDAO {
	private Session sessao;
	
	public MedicoDAO() {
	}

	public MedicoDAO(Session session) {
		this.sessao = session;
	}
	
	public Serializable salvar(Medico medico)
	{
		Transaction trans = sessao.beginTransaction();
		Serializable cod = sessao.save(medico);
		trans.commit();
		return cod;
	}
	
	public void atualizar(Medico medico)
	{
		Transaction trans = sessao.beginTransaction();
		sessao.update(medico);
		trans.commit();
	}
	
	public void excluir(Medico medico)
	{
		Transaction trans = sessao.beginTransaction();
		sessao.delete(medico);
		trans.commit();
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
	
	// Procedimento para resgatar os dados de o médico que tem 'nome' como parte do nome
	public Medico getMedico(String nome)
	{
		// https://stackoverflow.com/questions/28555942/hibernate-query-for-searching-part-of-string
		Query consulta = sessao.createQuery("from medico where nome like :id");
		consulta.setParameter("id", "%"+nome+"%");
		
		return (Medico) consulta.uniqueResult();
	}

	// Procedimento para resgatar os dados de todos os médicos que tenham 'nome' como parte do nome
	public List<Medico> getMedicos(String nome)
	{
		// https://stackoverflow.com/questions/28555942/hibernate-query-for-searching-part-of-string
		Query consulta = sessao.createQuery("from medico where nome like :id");
		consulta.setParameter("id", "%"+nome+"%");
		
		return consulta.list();
	}
	
	public List<Consulta> getConsultas(Integer codigo)
	{
		return getMedico(codigo).getConsultas();
	}
	
	// Retorna todos os receituários relacionados com o médico
	public List<ReceituarioMedico> getReceituarios(Integer codigo)
	{
		List<Consulta> consultas = getConsultas(codigo);
		List<ReceituarioMedico> receituarios = new ArrayList<ReceituarioMedico>();
		ConsultaDAO csDAO = new ConsultaDAO(sessao);
		
		for(Consulta consulta : consultas)
		{
			receituarios.addAll(csDAO.getReceituariosMedicos(consulta.getCodigo()));
		}
		
		return receituarios;
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

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}
	
	
}
