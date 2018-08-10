package folhadepagamento;

import java.util.Date;
import java.util.HashSet;

import controlefuncionarios.Funcionario;
import controlefuncionarios.FuncionarioComissionado;
import controlefuncionarios.FuncionarioMensalista;

public class RepositorioDeDados {
	private HashSet<Funcionario> funcionarios;

	public RepositorioDeDados() {
		this.funcionarios = new HashSet<>();
	}

	public boolean adicionaFuncionario(int opcao, String nome, int cpf, double salario) {
		Date hoje = new Date();
		if (opcao == 1) {
			this.funcionarios.add(new FuncionarioComissionado(nome, cpf, hoje, salario));
			return true;
		} else if (opcao == 2) {
			this.funcionarios.add(new FuncionarioMensalista(nome, cpf, hoje, salario));
			return true;
		}
		return false;
	}

	public Funcionario getFuncionario(int numcpf) {
		for (Funcionario f : this.funcionarios) {
			if (numcpf == f.getCpf()) {
				return f;
			}
		}
		return null;
	}

	public boolean removeFuncionario(int numcpf) {
		Funcionario f = this.getFuncionario(numcpf);
		if (f != null) {
			this.funcionarios.remove(f);
			return true;
		}
		return false;
	}
}
