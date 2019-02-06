package br.com.iftm.entily;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity // persistencia
//define informações sobre a tabela
@Table(name = "TB_TIPOQUARTO", uniqueConstraints = {
		@UniqueConstraint(name = "UNQ_TIPO_QUARTO", columnNames = { "DESCRICAO_TIPOQUARTO" }) })
//UniqueConstraint evita que crie mais de um dado com o mesmo valor

//configuração de sequence
@SequenceGenerator(name = "SQ_TIPOQUARTO", sequenceName = "SQ_TIPOQUARTO", initialValue = 1, allocationSize = 1)
public class TipoQuarto {

	@Id // utilizado como chave primária
	@GeneratedValue(generator = "SQ_TIPOQUARTO", strategy = GenerationType.SEQUENCE) // geração da chave primária
	@Column(name = "CODIGO_TIPOQUARTO") // precisa dar nome pra coluna (no caso do ID)
	private Integer codigo;

	// mapeamento de atributos das classes com colunas no banco
	@Column(name = "DESCRICAO_TIPOQUARTO", nullable = false, length = 100)
	private String descricao;

	@Column(name = "VALOR_TIPOQUARTO", nullable = false)
	private Integer valor;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

}
