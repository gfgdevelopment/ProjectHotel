package br.com.iftm.entily;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity // persistencia
@Table(name = "TB_CLIENTE")
public class Cliente {

	@Id // utilizado como chave primária
	@GeneratedValue(generator = "SQ_CLIENTE", strategy = GenerationType.SEQUENCE) // geração da chave primária
	@Column(name = "CODIGO_CLIENTE") // precisa dar nome pra coluna (no caso do ID)
	private Integer codigo;

	// mapeamento de atributos das classes com colunas no banco
	@Column(name = "NOME_CLIENTE", nullable = false, length = 100)
	private String nome;

	// mapeamento de atributos das classes com colunas no banco
	@Column(name = "CPF_CLIENTE", nullable = false, length = 11)
	private String cpf;

	// mapeamento de atributos das classes com colunas no banco
	@Column(name = "SEXO_CLIENTE", nullable = false, length = 1)
	private String sexo;

	// mapeamento de atributos das classes com colunas no banco
	@Column(name = "EMAIL_CLIENTE", nullable = true, length = 120)
	private String email;

	// relação Um pra Muitos
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "clientes", orphanRemoval = true, targetEntity = Telefone.class)
	private Set<Telefone> telefones;

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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

}
