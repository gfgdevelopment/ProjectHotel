package br.com.iftm.entily;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.iftm.enums.Status;

@Entity // persistencia
@Table(name = "TB_QUARTO", uniqueConstraints = {
		@UniqueConstraint(name = "UNQ_QUARTO", columnNames = { "CODIGO_TIPOQUARTO", "NUMERO" }) })
public class Quarto {

	@Id // utilizado como chave primária
	@GeneratedValue(generator = "SQ_QUARTO", strategy = GenerationType.SEQUENCE) // geração da chave primária
	@Column(name = "CODIGO_QUARTO") // precisa dar nome pra coluna (no caso do ID)
	private Integer codQuarto;

	@Column(name = "NUMERO", nullable = false)
	private Integer numero;

	// mapeamento de atributos das classes com colunas no banco
	@Column(name = "ANDAR", nullable = false, length = 40)
	private String andar;

	// mapeamento de atributos das classes com colunas no banco
	@Column(name = "STATUS", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private Status status;

	// relação Muitos pra Um
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = TipoQuarto.class)
	@JoinColumn(name = "CODIGO_TIPOQUARTO", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "FK_TIPOQUARTO"))
	private TipoQuarto tipoQuarto;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TipoQuarto getTipoQuarto() {
		return tipoQuarto;
	}

	public void setTipoQuarto(TipoQuarto tipoQuarto) {
		this.tipoQuarto = tipoQuarto;
	}

	public Integer getCodQuarto() {
		return codQuarto;
	}

	public void setCodQuarto(Integer codQuarto) {
		this.codQuarto = codQuarto;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getAndar() {
		return andar;
	}

	public void setAndar(String andar) {
		this.andar = andar;
	}

}
