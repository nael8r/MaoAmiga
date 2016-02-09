package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name="consulta")
public class Consulta implements Serializable {

	private static final long serialVersionUID = 6904949849654075665L;
	
	@Id @GeneratedValue
	private Integer codigo;
	// Agenda
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date data;
	@Temporal(TemporalType.TIME)
	@Column(nullable=false)
	private Date hora;
	@Column(name="tipo_consulta", nullable=false)
	private Character tipoConsulta;
	
	// Pré-consulta
	//@Column(nullable=false)
	private Float peso;
	//@Column(nullable=false)
	private Float altura;
	//@Column(nullable=false)
	private Float temperatura;
	//@Column(nullable=false)
	@Column(name="pressao_arterial", length=10)
	private String pressaoArterial;
	
	// Prontuário
	@Lob
	//@Column(nullable=false)
	private String queixa;
	//@Column(nullable=false)
	private Boolean usoMedicamentos;
	//@Column(nullable=false)
	private Character pessoais;
	@Lob
	//@Column(nullable=false)
	@Column(name="anotacoes_finais")
	private String anotacoesFinais;
	//@Column(nullable=false)
	private Boolean atestado;
	// chaves estrangeiras
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
	
	public Consulta() {
	}
	
	public Consulta(Integer codigo, Date data, Date hora, Character tipoConsulta, Float peso, Float altura,
			Float temperatura, String pressaoArterial, String queixa, Boolean usoMedicamentos, Character pessoais,
			String anotacoesFinais, Boolean atestado) {
		super();
		this.codigo = codigo;
		this.data = data;
		this.hora = hora;
		this.tipoConsulta = tipoConsulta;
		this.peso = peso;
		this.altura = altura;
		this.temperatura = temperatura;
		this.pressaoArterial = pressaoArterial;
		this.queixa = queixa;
		this.usoMedicamentos = usoMedicamentos;
		this.pessoais = pessoais;
		this.anotacoesFinais = anotacoesFinais;
		this.atestado = atestado;
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

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Character getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(Character tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
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

	public String getQueixa() {
		return queixa;
	}

	public void setQueixa(String queixa) {
		this.queixa = queixa;
	}

	public Boolean getUsoMedicamentos() {
		return usoMedicamentos;
	}

	public void setUsoMedicamentos(Boolean usoMedicamentos) {
		this.usoMedicamentos = usoMedicamentos;
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
		Consulta other = (Consulta) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Consulta [codigo=" + codigo + ", data=" + data + ", hora=" + hora + ", tipoConsulta=" + tipoConsulta
				+ ", peso=" + peso + ", altura=" + altura + ", temperatura=" + temperatura + ", pressaoArterial="
				+ pressaoArterial + ", queixa=" + queixa + ", usoMedicamentos=" + usoMedicamentos + ", pessoais="
				+ pessoais + ", anotacoesFinais=" + anotacoesFinais + ", atestado=" + atestado
				+ ", receituariosMedicos=" + receituariosMedicos + ", receituariosExames=" + receituariosExames + "]";
	}
	
	

}
