package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity(name="paciente")
public class Paciente implements Serializable {

	private static final long serialVersionUID = -6594217200682507286L;
	@Id @GeneratedValue
	private Integer codigo;
	@Column(length=50, nullable=false)
	private String nome;
	@Column(length=11, nullable=false)
	private String cpf;
	@Column(length=11, nullable=false)
	private String rg;
	@OneToMany(mappedBy="paciente",
			cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	private List<Espera> esperas;
	@OneToMany(mappedBy="paciente",
			cascade=CascadeType.ALL,
			fetch=FetchType.EAGER)
	private List<Agenda> agendas;
	
	public Paciente() {
	}

	public Paciente(String nome, String cpf, String rg) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public List<Espera> getEsperas() {
		return esperas;
	}

	public void setEsperas(List<Espera> esperas) {
		this.esperas = esperas;
	}

	public List<Agenda> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
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
		Paciente other = (Paciente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Paciente [nome=" + nome + ", esperas=" + esperas + ", agendas=" + agendas + "]";
	}

}