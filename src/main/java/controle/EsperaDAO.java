package controle;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Espera;
import modelo.Paciente;
import modelo.ReceituarioMedico;

/*
	Servlet de controle de comunicação com o banco de dados da classe Espera
*/
public class EsperaDAO {
	
	private Session sessao;

	public EsperaDAO(Session sessao) {
		this.sessao = sessao;
	}
	
	public Serializable salvar(Espera espera)
	{
		Transaction trans = sessao.beginTransaction();
		Serializable cod = sessao.save(espera);
		trans.commit();
		return cod;
	}
	
	public void update(Espera espera)
	{
		Transaction trans = sessao.beginTransaction();
		sessao.update(espera);
		trans.commit();
	}
	
	public void excluir(Espera espera)
	{
		Transaction trans = sessao.beginTransaction();
		sessao.delete(espera);
		trans.commit();
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
	
	public void excluirEsperaDaConsulta(int codigoPaciente, long milissegundos)
	{
		Date data = new Date(milissegundos);
		List<Espera> e = new ArrayList<>();
		
		Query consulta = sessao.createQuery("from espera");
		
		Iterator<Espera> iter = consulta.list().iterator();
		
		Transaction t = sessao.beginTransaction();
		
		while(iter.hasNext()) {
			Espera buff = iter.next();
			Espera buff_remove = buff;

		   if (buff_remove.getPaciente().getCodigo() == codigoPaciente && buff_remove.getData().equals(data)) {
			   sessao.delete(buff_remove);
			   iter.remove();
		   }
		}
		
		t.commit();
	}
	
	public List<Espera> getEsperaDoPaciente(Integer codigo)
	{
		Query consulta = sessao.createQuery("from espera where cod_paciente = :cod_param");
		consulta.setInteger("cod_param", codigo);

		return consulta.list();
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

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

}
