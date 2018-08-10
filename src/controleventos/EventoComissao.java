package controleventos;

import java.util.Date;
import excecoes.FolhaException;

public class EventoComissao extends Evento {
	public EventoComissao(Date dt, double valor) throws FolhaException {
		super(dt, valor);
		if (valor <= 0)
			throw new FolhaException("Comiss�o com valor menor ou igual a 0");

	}
}