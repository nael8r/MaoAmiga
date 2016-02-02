package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

public class Consulta implements Serializable {
	
	@ManyToOne(optional=false)
	@JoinColumn(name="cod_paciente")
	private Paciente paciente;
	@ManyToOne(optional=false)
	@JoinColumn(name="cod_medico")
	private Medico medico;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="consulta",
			cascade=CascadeType.ALL)
	private List<ReceituarioMedico> receituariosMedicos;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="consulta",
			cascade=CascadeType.ALL)
	private List<ReceituarioExames> receituariosExames;
	
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
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

}
