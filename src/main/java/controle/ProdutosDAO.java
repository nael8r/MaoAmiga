package controle;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

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
		return sessao.save(produtos);
	}
	
	public void atualizar(Produtos produtos)
	{
		sessao.update(produtos);
	}
	
	public void excluir(Produtos produtos)
	{
		sessao.delete(produtos);
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
	
	public List<ReceituarioMedico> getReceituariosMedicos(Integer codigo)
	{
		try {
			return getProduto(codigo).getReceituariosMedicos();
		} catch (NullPointerException e) {
			return null;
		}
	}

}
