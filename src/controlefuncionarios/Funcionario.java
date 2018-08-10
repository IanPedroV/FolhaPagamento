package controlefuncionarios;

import controleventos.Evento;

import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;

import empresaconveniada.InterfaceConvenio;
import excecoes.FolhaException;
import folhadepagamento.Demonstrativo;

public abstract class Funcionario {
	protected String nomeFunc;
	protected double salarioBase;
	protected Date dtAdmissao;
	protected Date dtSalario;
	protected Date dtFechamento;
	protected Date dtRescisao;
	protected ArrayList<Evento> eventos;
	protected ArrayList<Demonstrativo> demonstrativos;
	protected InterfaceConvenio convenio;
	protected String idContrato;
	protected int cpf;

	public Funcionario(String nome, int cpf, Date dt, double salario) {
		this.nomeFunc = nome;
		this.cpf = cpf;
		this.dtAdmissao = dt;
		this.dtFechamento = dt;
		this.dtSalario = dt;
		this.salarioBase = salario;
		this.eventos = new ArrayList<>();
		this.demonstrativos = new ArrayList<>();
	}

	public void registraEvento(Evento e) throws FolhaException {
		Date hoje = new Date();
		if (dtRescisao != null) {
			throw new FolhaException("Evento para funcionario já desligado.", this, e);

		} else if (!(e.getDtEvento()).after(dtFechamento)) {
			throw new FolhaException("Evento com data anterior aa do fechamento.", this, e);

		} else if ((e.getDtEvento()).after(hoje)) {
			throw new FolhaException("Evento com data futura", this, e);
		} else if (eventoDuplicado(e)) {
			throw new FolhaException("Evento duplicado para o funcionario.", this, e);

		}
		this.eventos.add(e);
	}

	private boolean eventoDuplicado(Evento e) {
		Iterator<Evento> lista = this.eventos.iterator();
		while (lista.hasNext()) {
			if (e.equals((Evento) lista.next()))
				return true;

		}
		return false;
	}

	public int getCpf() {
		return cpf;
	}

	public void registraConvenio(InterfaceConvenio c, String id) {
		convenio = c;
		idContrato = id;
	}

	public void geraDemonstrativo() throws FolhaException {
		Evento e;
		Demonstrativo d;
		Date hoje = new Date();
		Iterator<Evento> lista = eventos.iterator();
		while (lista.hasNext()) {
			e = (Evento) lista.next();
			if ((e.getDtEvento()).after(dtFechamento)) {
				if (!eventoComum(e))
					try {
						processaEvento(e);
					} catch (FolhaException exc) {
						System.out.println(exc);
					}

			}
		}
		d = new Demonstrativo(this, dtFechamento, hoje);
		try {
			d.incluiCredito("Salario base", this.salarioBase);
		} catch (Exception ex) {
			throw new FolhaException("Erro ao lancar salario base",

					this, ex);

		}
		geraLancamentos(d);

		if (idContrato != null) {
			try {
				convenio.processaContrato(idContrato, d);
			} catch (Exception ex) {
				System.out.println("Erro ao lancar convenio - contrato"

						+ idContrato);

			}
		}

		this.demonstrativos.add(d);
		this.dtFechamento = hoje;
		d.imprime();
	}

	private boolean eventoComum(Evento e) {
		if (e.getTipoEvento().equals("EventoReajuste")) {
			dtSalario = e.getDtEvento();
			salarioBase = e.getValorEvento();
			return true;
		} else if (e.getTipoEvento().equals("EventoRescisao")) {
			dtRescisao = e.getDtEvento();
			return true;
		} else
			return false;
	}

	public String toString() {
		return ("Funcionario: " + this.nomeFunc);
	}

	public abstract void processaEvento(Evento evento) throws FolhaException;

	abstract void geraLancamentos(Demonstrativo d) throws FolhaException;
}