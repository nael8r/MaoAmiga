package controle;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Consulta;
import modelo.ReceituarioMedico;

public class ReceituarioMedicoDAO {
	
	private Session sessao;

	public ReceituarioMedicoDAO(Session sessao) {
		this.sessao = sessao;
	}
	
	public Serializable salvar(ReceituarioMedico rm)
	{
		Transaction trans = sessao.beginTransaction();
		Serializable cod = sessao.save(rm);
		trans.commit();
		return cod;
	}
	
	public void atualiza(ReceituarioMedico rm)
	{
		Transaction trans = sessao.beginTransaction();
		sessao.update(rm);
		trans.commit();
	}
	
	public void excluir(ReceituarioMedico rm)
	{
		Transaction trans = sessao.beginTransaction();
		sessao.delete(rm);
		trans.commit();
	}
	
	public void excluirReceituarioDaConsulta(Consulta c)
	{	
		List<ReceituarioMedico> todos = sessao.createQuery("from receituario_medico").list();
		
		Iterator<ReceituarioMedico> i = todos.iterator();

		Transaction trans = sessao.beginTransaction();
		
		while (i.hasNext()) {
		   ReceituarioMedico rm = i.next(); 
		   ReceituarioMedico rm_Remover = rm; 
		   
		   if (rm.getConsulta().getCodigo() == c.getCodigo()) {
			   sessao.delete(rm_Remover);
			   i.remove();
		   }
		}
		trans.commit();
	}
	
	public List<ReceituarioMedico> getReceituarios()
	{
		Query consulta = sessao.createQuery("from receituario_medico");
		
		return consulta.list();
	}
	
	public List<ReceituarioMedico> getReceituarios(Integer limite)
	{
		Query consulta = sessao.createQuery("from receituario_medico");
		consulta.setMaxResults(limite);
		
		return consulta.list();
	}
	
	public ReceituarioMedico getReceituario(Integer codigo)
	{
		Query consulta = sessao.createQuery("from receituario_medico where codigo = :cod_param");
		consulta.setInteger("cod_param", codigo);
		
		return (ReceituarioMedico)consulta.uniqueResult();
	}

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

}
