package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name="prontuario")
public class Prontuario implements Serializable {

	private static final long serialVersionUID = 7907510768746878124L;
	@Id @GeneratedValue
	private Integer codigo;
	@Lob
	@Column(nullable=false)
	private String queixa;
	@Column(nullable=false)
	private Character pessoais;
	@Lob
	@Column(nullable=false, name="anotacoes_finais")
	private String anotacoesFinais;
	@Column(nullable=false)
	private Boolean atestado;
	@OneToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(name="agenda_codigo")
	private Agenda agenda;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="prontuario",
			cascade=CascadeType.ALL)
	private List<ReceituarioMedico> receituariosMedicos;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="prontuario",
			cascade=CascadeType.ALL)
	private List<ReceituarioExames> receituariosExames;
	
	public Prontuario() {
	}

	public Prontuario(String queixa, Character pessoais, String anotacoesFinais, Boolean atestado) {
		super();
		this.queixa = queixa;
		this.pessoais = pessoais;
		this.anotacoesFinais = anotacoesFinais;
		this.atestado = atestado;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getQueixa() {
		return queixa;
	}

	public void setQueixa(String queixa) {
		this.queixa = queixa;
	}

	public Character getPessoais() {
		return pessoais;
	}

	public void setPessoais(Character pessoais) {
		this.pessoais = pessoais;
	}

	public String getAnotacoesFinais() {
		return anotacoesFinais;
	}

	public void setAnotacoesFinais(String anotacoesFinais) {
		this.anotacoesFinais = anotacoesFinais;
	}

	public Boolean getAtestado() {
		return atestado;
	}

	public void setAtestado(Boolean atestado) {
		this.atestado = atestado;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public List<ReceituarioMedico> getReceituariosMedicos() {
		return receituariosMedicos;
	}

	public void setReceituariosMedicos(List<ReceituarioMedico> receituariosMedicos) {
		this.receituariosMedicos = receituariosMedicos;
	}

	public List<ReceituarioExames> getReceituariosExames() {
		return receituariosExames;
	}

	public void setReceituariosExames(List<ReceituarioExames> receituariosExames) {
		this.receituariosExames = receituariosExames;
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
		Prontuario other = (Prontuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Prontuario [queixa=" + queixa + ", pessoais=" + pessoais + ", anotacoesFinais=" + anotacoesFinais
				+ ", atestado=" + atestado + ", agenda=" + agenda + ", receituariosMedicos=" + receituariosMedicos
				+ ", receituariosExames=" + receituariosExames + "]";
	}
	
	

}
