package br.com.iftm.entily;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // persistencia
@Table(name = "TB_TELEFONE_CLIENTE", uniqueConstraints = {
		@UniqueConstraint(name = "UNQ_TELEFONE_CLIENTE", columnNames = { "CODIGO_CLIENTE", "DDD_TELEFONE",
				"NUMERO_TELEFONE" }) })

@SequenceGenerator(name = "SQ_TELEFONE_CLIENTE", sequenceName = "SQ_TELEFONE_CLIENTE", initialValue = 1, allocationSize = 1)
@JsonIgnoreProperties(value = { "clientes" }) // problema no Json
public class Telefone {

	@Id // utilizado como chave primária
	@GeneratedValue(generator = "SQ_TELEFONE_CLIENTE", strategy = GenerationType.SEQUENCE) // geração da chave primária
	@Column(name = "CODIGO_TELEFONE_CLIENTE") // precisa dar nome pra coluna (no caso do ID)
	private Integer codigo;

	// mapeamento de atributos das classes com colunas no banco
	@Column(name = "DDD_TELEFONE", nullable = false, length = 2)
	private Integer ddd;

	@Column(name = "NUMERO_TELEFONE", nullable = false, length = 9)
	private Integer numero;

	// relação Muitos pra Um
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Cliente.class)
	@JoinColumn(name = "CODIGO_CLIENTE", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "FK_TEL_CLIENTE"))
	private Cliente clientes;

	public Cliente getClientes() {
		return clientes;
	}

	public void setClientes(Cliente clientes) {
		this.clientes = clientes;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}
