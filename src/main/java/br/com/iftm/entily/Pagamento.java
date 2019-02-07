package br.com.iftm.entily;

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
//define informações sobre a tabela
@Table(name = "TB_PAGAMENTO", uniqueConstraints = { @UniqueConstraint(name = "UNQ_PAGAMENTO", columnNames = {
		"CODIGO_RESERVA", "FORM_PAGAMENTO", "VALOR_PAGAMENTO" }) })
//UniqueConstraint evita que crie mais de um dado com o mesmo valor

@SequenceGenerator(name = "SQ_PAGAMENTO", sequenceName = "SQ_PAGAMENTO", initialValue = 1, allocationSize = 1)
@JsonIgnoreProperties(value = { "reservas" }) // problema no Json
public class Pagamento {
	@Id // utilizado como chave primária
	@GeneratedValue(generator = "SQ_PAGAMENTO", strategy = GenerationType.SEQUENCE) // geração da chave primária
	@Column(name = "CODIGO_PAGAMENTO") // precisa dar nome pra coluna (no caso do ID)
	private Integer codigo;
	// mapeamento de atributos das classes com colunas no banco
	@Column(name = "FORM_PAGAMENTO", nullable = true, length = 50)
	private String formaDePagamento;
	// mapeamento de atributos das classes com colunas no banco
	@Column(name = "VALOR_PAGAMENTO", nullable = true)
	private Double valor;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Reserva.class)
	@JoinColumn(name = "CODIGO_RESERVA", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "FK_PAG_RESERVA"))
	private Reserva reservas;

	public Reserva getReservas() {
		return reservas;
	}

	public void setReservas(Reserva reservas) {
		this.reservas = reservas;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
