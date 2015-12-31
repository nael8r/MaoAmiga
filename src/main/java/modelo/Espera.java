package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity(name="espera")
public class Espera implements Serializable {

	private static final long serialVersionUID = 4363136501653697837L;
	@Id @GeneratedValue
	private Integer codigo;
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date data;
	@ManyToOne(optional=false)
	@JoinColumn(name="cod_paciente")
	private Paciente paciente;
	
	public Espera() {
		
	}

	public Espera(Date data, Paciente paciente) {
		super();
		this.data = data;
		this.paciente = paciente;
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
		this.data = data;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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
		Espera other = (Espera) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Espera [data=" + data + "]";
	}
	
	
	
}
