package controle;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Medico;
import modelo.Produtos;
import modelo.ReceituarioMedico;

public class ProdutosDAO {
	
	private Session sessao;

	public ProdutosDAO(Session sessao) {
		super();
		this.sessao = sessao;
	}
	
	public Serializable salvar(Produtos produtos)
	{
		Transaction trans = sessao.beginTransaction();
		Serializable cod = sessao.save(produtos);
		trans.commit();
		return cod;
	}
	
	public void atualizar(Produtos produtos)
	{
		Transaction trans = sessao.beginTransaction();
		sessao.update(produtos);
		trans.commit();
	}
	
	public void excluir(Produtos produtos)
	{
		Transaction trans = sessao.beginTransaction();
		sessao.delete(produtos);
		trans.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Produtos> getProdutos()
	{
		Query consulta = sessao.createQuery("from produtos");
		
		return consulta.list();
	}
	
	public List<Produtos> getProdutos(Integer limite)
	{
		Query consulta = sessao.createQuery("from produtos");
		consulta.setMaxResults(limite);
		
		return consulta.list();
	}
	
	public Produtos getProduto(Integer codigo)
	{
		Query consulta = sessao.createQuery("from produtos where codigo = :cod_param");
		consulta.setInteger("cod_param", codigo);
		
		return (Produtos)consulta.uniqueResult();
	}
	
	public Produtos getProduto(String nome)
	{
		Query consulta = sessao.createQuery("from produtos where nome = :cod_param");
		consulta.setString("cod_param", nome);
		
		return (Produtos)consulta.uniqueResult();
	}

	public List<Produtos> getProdutos(String nome)
	{
		// https://stackoverflow.com/questions/28555942/hibernate-query-for-searching-part-of-string
		Query consulta = sessao.createQuery("from produtos where nome like :id");
		consulta.setParameter("id", "%"+nome+"%");
		
		return consulta.list();
	}
	
	
	public Session getSessao() {
		return sessao;
	}

	public void setSessao(Session sessao) {
		this.sessao = sessao;
	}

	public List<ReceituarioMedico> getReceituariosMedicos(Integer codigo)
	{
		try {
			return getProduto(codigo).getReceituariosMedicos();
		} catch (NullPointerException e) {
			return null;
		}
	}

}
