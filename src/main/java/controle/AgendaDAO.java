package controle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import modelo.Agenda;

public class AgendaDAO {
	
	private Session session;

	public AgendaDAO(Session session) {
		super();
		this.session = session;
	}
	
	public Serializable salvar(Agenda agenda)
	{
		return session.save(agenda);
	}
	
	public void excluir(Agenda agenda)
	{
		session.delete(agenda);
	}

	public void atualizar(Agenda agenda)
	{
		session.update(agenda);
	}
	
	@SuppressWarnings("unchecked")
	public List<Agenda> getAgendas()
	{
		Query consulta = session.createQuery("from agenda");
		
		return consulta.list();
	}
	
	public Agenda getAgenda(Integer codigo)
	{
		Query consulta = session.createQuery("from agenda where codigo = :cod_param");
		consulta.setInteger("cod_param", codigo);
		
		return (Agenda) consulta.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Agenda> getAgendasPorData(Date data)
	{
		data = util.DateTimeFormat.formatDataParaBR(data);
		
		String hql = "from agenda where data = :data_param";
		Query consulta = session.createQuery(hql);
		consulta.setDate("data_param", data);
		
		return consulta.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Agenda> getAgendasEntreDatas(Date comeco, Date fim)
	{
		comeco = util.DateTimeFormat.formatDataParaBR(comeco);
		fim = util.DateTimeFormat.formatDataParaBR(fim);
		
		String hql = "from agenda where data >= :comecoData_param and data <= :fimData_param";
		Query consulta = session.createQuery(hql);
		consulta.setDate("comecoData_param", comeco);
		consulta.setDate("fimData_param", fim);
		
		return consulta.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Agenda> getAgendasPorHora(Date hora)
	{
		hora = util.DateTimeFormat.formatDataParaBR(hora);
		
		String hql = "from agenda where hora = :hora_param";
		Query consulta = session.createQuery(hql);
		consulta.setDate("hora_param", hora);
		
		return consulta.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Agenda> getAgendasEntreHoras(Date comeco, Date fim)
	{
		comeco = util.DateTimeFormat.formatDataParaBR(comeco);
		fim = util.DateTimeFormat.formatDataParaBR(fim);
		
		String hql = "from agenda where hora >= :comecoHora_param and data <= :fimHora_param";
		Query consulta = session.createQuery(hql);
		consulta.setDate("comecoHora_param", comeco);
		consulta.setDate("fimHora_param", fim);
		
		return consulta.list();
	}
	
}
