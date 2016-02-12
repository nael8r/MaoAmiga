package modelo;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name="receituario_medico")
public class ReceituarioMedico implements Serializable {
	
	private static final long serialVersionUID = -6428859897486544367L;
	@Id @GeneratedValue
	private Integer codigo;
	
	@Lob
	@Column(nullable=false)
	private String medicamentos;
	
	@ManyToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(name="cod_consulta")
	private Consulta consulta;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="cod_produtos")
	private Produtos produtos;
	
	public ReceituarioMedico() {
	}

	public ReceituarioMedico(String medicamentos) {
		super();
		this.medicamentos = medicamentos;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(String medicamentos) {
		this.medicamentos = medicamentos;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
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
		ReceituarioMedico other = (ReceituarioMedico) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReceituarioMedico [medicamentos=" + medicamentos + "]";
	}
	
}
