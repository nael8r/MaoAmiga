package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity(name="agenda")
public class Agenda implements Serializable {

	private static final long serialVersionUID = 4467444152439753376L;
	@Id @GeneratedValue
	private Integer codigo;
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date data;
	@Temporal(TemporalType.TIME)
	@Column(nullable=false)
	private Date hora;
	@Column(name="tipo_consulta", nullable=false)
	private Character tipoConsulta;
	@Column(nullable=false)
	private Integer ano;
	@Column(nullable=false)
	private Integer mes;
	@ManyToOne(optional=false)
	@JoinColumn(name="cod_paciente")
	private Paciente paciente;
	@ManyToOne(optional=false)
	@JoinColumn(name="cod_medico")
	private Medico medico;
	@OneToOne(mappedBy="agenda")
	private PreConsulta preConsulta;
	@OneToOne(mappedBy="agenda")
	private Prontuario prontuario;
	
	public Agenda() {
	}

	public Agenda(Date data, Date hora, Character tipoConsulta, Integer ano, Integer mes) {
		super();
		this.data = util.DateTimeFormat.formatDataParaBR(data);
		this.hora = util.DateTimeFormat.formatDataParaBR(hora);
		this.tipoConsulta = tipoConsulta;
		this.ano = ano;
		this.mes = mes;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = util.DateTimeFormat.formatDataParaBR(data);
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = util.DateTimeFormat.formatDataParaBR(hora);
	}

	public Character getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(Character tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

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

	public PreConsulta getPreConsulta() {
		return preConsulta;
	}

	public void setPreConsulta(PreConsulta preConsulta) {
		this.preConsulta = preConsulta;
	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
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
		Agenda other = (Agenda) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Agenda [data=" + data + ", hora=" + hora + ", tipoConsulta=" + tipoConsulta + ", ano=" + ano + ", mes="
				+ mes + "]";
	}
	
	
}
