package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/*
	Classe de Produtos com as anotações do Hibernate para criação e especificação 
		do banco de dados
*/

@Entity(name="produtos")
public class Produtos implements Serializable {

	private static final long serialVersionUID = 1419690007796173533L;
	@Id @GeneratedValue
	private Integer codigo;
	@Column(length=50, nullable=false)
	private String nome;
	@OneToMany(mappedBy="produtos",
			cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	private List<ReceituarioMedico> receituariosMedicos;
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<ReceituarioMedico> getReceituariosMedicos() {
		return receituariosMedicos;
	}
	public void setReceituariosMedicos(List<ReceituarioMedico> receituariosMedicos) {
		this.receituariosMedicos = receituariosMedicos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produtos other = (Produtos) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Produtos [nome=" + nome + ", receituariosMedicos=" + receituariosMedicos + "]";
	}
	
	
}
