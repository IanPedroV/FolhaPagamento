package controlefuncionarios;

import controleventos.Evento;
import java.util.Date;
import excecoes.FolhaException;
import folhadepagamento.Demonstrativo;

public class FuncionarioComissionado extends Funcionario {
	private double comissoes;

	public FuncionarioComissionado(String nome, int cpf, Date dt, double salario) {
		super(nome, cpf, dt, salario);
	}

	public void processaEvento(Evento e) throws FolhaException {
		if (e.getTipoEvento().equals("EventoComissao"))
			comissoes += e.getValorEvento();
		else
			throw new FolhaException("Evento invalido para comissionado.", this, e);
	}

	public void geraLancamentos(Demonstrativo d) throws FolhaException {
		try {
			if (comissoes > 0)
				d.incluiCredito("Comissoes", comissoes);

		} catch (Exception ex) {
			throw new FolhaException("Erro ao gerar lancamento de comissoes.", this, ex);

		}
	}
}