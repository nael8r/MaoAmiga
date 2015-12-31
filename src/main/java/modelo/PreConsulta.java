package modelo;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name="pre_consulta")
public class PreConsulta implements Serializable {

	private static final long serialVersionUID = -8717600130022152865L;
	@Id @GeneratedValue
	private Integer codigo;
	@Column(nullable=false)
	private Float peso;
	@Column(nullable=false)
	private Float altura;
	@Column(nullable=false)
	private Float temperatura;
	@Column(name="pressao_arterial", nullable=false, length=10)
	private String pressaoArterial;
	@OneToOne(optional=false, cascade=CascadeType.ALL)
	@JoinColumn(name="agenda_codigo")
	private Agenda agenda;
	
	public PreConsulta() {
		
	}

	public PreConsulta(Float peso, Float altura, Float temperatura, String pressaoArterial) {
		super();
		this.peso = peso;
		this.altura = altura;
		this.temperatura = temperatura;
		this.pressaoArterial = pressaoArterial;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public Float getAltura() {
		return altura;
	}

	public void setAltura(Float altura) {
		this.altura = altura;
	}

	public Float getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Float temperatura) {
		this.temperatura = temperatura;
	}

	public String getPressaoArterial() {
		return pressaoArterial;
	}

	public void setPressaoArterial(String pressaoArterial) {
		this.pressaoArterial = pressaoArterial;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
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
		PreConsulta other = (PreConsulta) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PreConsulta [peso=" + peso + ", altura=" + altura + ", temperatura=" + temperatura
				+ ", pressaoArterial=" + pressaoArterial + ", agenda=" + agenda + "]";
	}
	
	
}
