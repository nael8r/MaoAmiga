package controle;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Consulta;
import modelo.Medico;
import modelo.Paciente;
import modelo.ReceituarioExames;
import modelo.ReceituarioMedico;

public class ReceituarioExamesDAO {
	
	private Session sessao;

	public ReceituarioExamesDAO(Session sessao) {
		this.sessao = sessao;
	}
	
	public Serializable salvar(ReceituarioExames re)
	{
		Transaction trans = sessao.beginTransaction();
		Serializable cod = sessao.save(re);
		trans.commit();
		return cod;
	}
	
	public void atualizar(ReceituarioExames re)
	{
		Transaction trans = sessao.beginTransaction();
		sessao.update(re);
		trans.commit();
	}
	
	public void excluir(ReceituarioExames re)
	{
		Transaction trans = sessao.beginTransaction();
		sessao.delete(re);
		trans.commit();
	}

	
	public void excluirReceituarioDaConsulta(Consulta c)
	{	
		List<ReceituarioExames> todos = sessao.createQuery("from receituario_exames").list();
		
		Iterator<ReceituarioExames> i = todos.iterator();

		Transaction trans = sessao.beginTransaction();
		
		while (i.hasNext()) {
			ReceituarioExames re = i.next(); 
			ReceituarioExames re_Remover = re; 
		   
		   if (re.getConsulta().getCodigo() == c.getCodigo()) {
			   sessao.delete(re_Remover);
			   i.remove();
		   }
		}
		trans.commit();
	}
	
	
	
	public List<ReceituarioExames> getReceituarios()
	{
		Query consulta = sessao.createQuery("from receituario_exames");
		
		return consulta.list();
	}
	
	
	public List<ReceituarioExames> getReceituariosDaConsulta(int codigo)
	{		

		Query consulta = sessao.createQuery("from receituario_exames where cod_consulta = :cod_param");
		consulta.setInteger("cod_param", codigo);
		
		return consulta.list();
	}
	
	public List<ReceituarioExames> getReceituarios(Integer limite)
	{
		Query consulta = sessao.createQuery("from receituario_exames");
		consulta.setMaxResults(limite);
		
		return consulta.list();
	}
	
	public ReceituarioExames getReceituario(Integer codigo)
	{
		Query consulta = sessao.createQuery("from receituario_exames where codigo = :cod_param");
		consulta.setInteger("cod_param", codigo);
		
		return (ReceituarioExames)consulta.uniqueResult();
	}

	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

}
