package controleventos;

import excecoes.FolhaException;
import java.util.Date;

public class EventoHoraExtra extends Evento {

	public EventoHoraExtra(Date dt, double qtdHoras) throws FolhaException {
		super(dt, qtdHoras);
		if (qtdHoras > 4)
			throw new FolhaException("Hora extra com quantidade de horas maior que 4.");
	}
}