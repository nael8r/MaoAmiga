package modelo;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name="receituario_exames")
public class ReceituarioExames implements Serializable {

	private static final long serialVersionUID = 6650966527901820005L;
	@Id @GeneratedValue
	private Integer codigo;
	@Lob
	@Column(nullable=false)
	private String exames;
	@ManyToOne(optional=false)
	@JoinColumn(name="cod_prontuario")
	private Prontuario prontuario;
	
	public ReceituarioExames() {
	}

	public ReceituarioExames(String exames) {
		super();
		this.exames = exames;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getExames() {
		return exames;
	}

	public void setExames(String exames) {
		this.exames = exames;
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
		ReceituarioExames other = (ReceituarioExames) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReceituarioExames [exames=" + exames + "]";
	}
	
	
}
