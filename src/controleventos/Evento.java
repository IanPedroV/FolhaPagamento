package controleventos;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Evento {

	private Date dtEvento;
	private double valorEvento;
	private int dia;
	private int mes;
	private int ano;

	public Evento(Date dt, double valor) {
		this.dtEvento = dt;
		Calendar cal = new GregorianCalendar();
		cal.setTime(dtEvento);
		this.dia = cal.get(Calendar.DATE);
		this.mes = cal.get(Calendar.MONTH) + 1;
		this.ano = cal.get(Calendar.YEAR) + 1900;
		this.valorEvento = valor;
	}

	public Date getDtEvento() {
		return this.dtEvento;
	}

	public double getValorEvento() {
		return this.valorEvento;
	}

	public String getTipoEvento() {
		return (this.getClass()).getName();
	}

	public boolean equals(Evento e) {
		return this.getTipoEvento().equals(e.getTipoEvento()) && this.dia == e.dia && this.mes == e.mes
				&& this.ano == e.ano;

	}

	public String toString() {
		return getTipoEvento() + " em " + this.dia + "/" + this.mes + "/" + this.ano + " valor=" + valorEvento;
	}
}
