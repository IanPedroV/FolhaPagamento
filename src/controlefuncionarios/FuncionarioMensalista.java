package controlefuncionarios;

import controleventos.Evento;
import java.util.Date;
import excecoes.FolhaException;
import folhadepagamento.Demonstrativo;

public class FuncionarioMensalista extends Funcionario {
	private double horasExtras;
	private double faltas;
	private double horasAtrasos;

	public FuncionarioMensalista(String nome, int cpf, Date dt, double salario) {
		super(nome, cpf, dt, salario);
	}

	public void processaEvento(Evento e) throws FolhaException {
		if (e.getTipoEvento().equals("EventoHoraExtra")) {
			this.horasExtras += e.getValorEvento();
		} else if (e.getTipoEvento().equals("EventoFalta")) {
			this.faltas++;
		} else if (e.getTipoEvento().equals("EventoAtraso")) {
			this.horasAtrasos += e.getValorEvento();
		} else {
			throw new FolhaException("Evento invalido para mensalista.", this, e);
		}
	}

	public void geraLancamentos(Demonstrativo d) throws FolhaException {
		double valor;
		double salarioHora;
		try {
			salarioHora = super.salarioBase / 176;
			if (this.horasExtras > 0) {
				valor = this.horasExtras * 2 * salarioHora;
				d.incluiCredito("Horas Extras (" + this.horasExtras + " hs)", valor);
			}
			if (this.faltas > 0) {
				valor = this.faltas * 8 * salarioHora;
				d.incluiDebito("Faltas (" + this.faltas + " dia)", valor);
			}
			if (this.horasAtrasos > 0) {
				valor = this.horasAtrasos * salarioHora;
				d.incluiDebito("Atrasos (" + this.horasAtrasos + " hs)", valor);
			}
		} catch (Exception ex) {
			throw new FolhaException("Erro ao gerar lancamentos do ponto.", this, ex);

		}
	}
}
