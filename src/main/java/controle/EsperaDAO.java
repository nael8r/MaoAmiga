package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import modelo.Espera;
import modelo.Paciente;

public class EsperaDAO {
	
	private Session sessao;

	public EsperaDAO(Session sessao) {
		this.sessao = sessao;
	}
	
	public Serializable salvar(Espera espera)
	{
		return sessao.save(espera);
	}
	
	public void update(Espera espera)
	{
		sessao.update(espera);
	}
	
	public void excluir(Espera espera)
	{
		sessao.delete(espera);
	}
	
	public List<Espera> getEsperas()
	{
		Query consulta = sessao.createQuery("from espera");
		
		return consulta.list();
	}
	
	public List<Espera> getEsperas(Integer limite)
	{
		Query consulta = sessao.createQuery("from espera");
		consulta.setMaxResults(limite);
		
		return consulta.list();
	}
	
	public Espera getEspera(Integer codigo)
	{
		Query consulta = sessao.createQuery("from espera where codigo = :cod_param");
		consulta.setInteger("cod_param", codigo);
		
		return (Espera)consulta.uniqueResult();
	}
	
	public List<Paciente> getPacientes()
	{
		List<Espera> esperas = getEsperas();
		List<Paciente> pacientes = new ArrayList<Paciente>();
		
		for(Espera espera : esperas)
		{
			pacientes.add(espera.getPaciente());
		}
		
		if(pacientes.isEmpty())
			return null;
		
		return pacientes;
	}
	

}
