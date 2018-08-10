package controleventos;

import java.util.Date;
import excecoes.FolhaException;

public class EventoReajuste extends Evento {
	public EventoReajuste(Date dt, double valor) throws FolhaException {
		super(dt, valor);
		if (valor < 300)
			throw new FolhaException("Reajuste com novo salario maior que 300.");
	}
}
