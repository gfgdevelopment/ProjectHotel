package br.com.iftm.entily;

import java.sql.Date;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity // persistencia
@Table(name = "TB_RESERVA")
public class Reserva {

	@Id // utilizado como chave primária
	@GeneratedValue(generator = "SQ_RESERVA", strategy = GenerationType.SEQUENCE) // geração da chave primária
	@Column(name = "CODIGO_RESERVA")
	private Integer codigo;

	@Column(name = "DATA_ENTRADA", nullable = false)
	@Type(type = "date")
	private Date dataEntrada;

	@Column(name = "DATA_SAIDA", nullable = false)
	@Type(type = "date")
	private Date dataSaida;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Cliente.class)
	@JoinColumn(name = "CODIGO_CLIENTE", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "FK_CLIENTE"))
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Quarto.class)
	@JoinColumn(name = "CODIGO_QUARTO", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "FK_QUARTO"))
	private Quarto quarto;

	// relação Um pra Muitos
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "reservas", orphanRemoval = true, targetEntity = Pagamento.class)
	private Set<Pagamento> pagamentos;

	public Set<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(Set<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

}
