package folhadepagamento;

public class Lancamento {
	private String historico;
	private double valor;

	public Lancamento(String st, double valor) {
		this.historico = st;
		this.valor = valor;
	}

	public String getHistorico() {
		return this.historico;
	}

	public double getValor() {
		return this.valor;
	}

	public String toString() {
		return (this.historico + "\t" + this.valor);
	}
}