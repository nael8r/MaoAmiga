package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity(name="medico")
public class Medico implements Serializable {

	private static final long serialVersionUID = -7727750082757889000L;
	@Id @GeneratedValue
	private Integer codigo;
	@Column(length=50, nullable=false)
	private String nome;
	@Column(length=50, nullable=false)
	private String endereco;
	@Column(length=20, nullable=false)
	private String telefone;
	@Column(length=20, nullable=false)
	private String crm;
	@Column(length=50, nullable=false)
	private String especialidade;
	@OneToMany(mappedBy="medico",
			cascade=CascadeType.ALL,
			fetch=FetchType.EAGER,
			orphanRemoval=true)
	private List<Consulta> consultas;
	
	public Medico() {
	}

	public Medico(String nome, String endereco, String telefone, String crm, String especialidade) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.crm = crm;
		this.especialidade = especialidade;
	}

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
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
		Medico other = (Medico) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Medico [nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + ", crm=" + crm
				+ ", especialidade=" + especialidade + ", consultas=" + consultas + "]";
	}
}
