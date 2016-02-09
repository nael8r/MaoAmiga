package modelo;

import java.io.Serializable;

import javax.persistence.*;

import org.jasypt.util.password.BasicPasswordEncryptor;

@Entity(name="usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1919618119866719613L;
	
	@Id @GeneratedValue
	private Integer codigo;
	@Column(length=50, nullable=false)
	private String nome;
	@Column(length=50, nullable=false)
	private String login;
	@Column(length=50, nullable=false)
	private String senha;
	@Column(nullable=false)
	private Character tipo;
	@Transient
	private BasicPasswordEncryptor bpe;
	
	public Usuario() {
		
	}

	public Usuario(String nome, String login, String senha, Character tipo) {
		super();
		bpe = new BasicPasswordEncryptor();
		this.nome = nome;
		this.login = login;
		//this.senha = bpe.encryptPassword(senha);
		this.senha = senha;
		this.tipo = tipo;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = bpe.encryptPassword(senha);
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
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
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", login=" + login + ", tipo=" + tipo + "]";
	}

}
