package folhadepagamento;

import java.util.Date;

public interface InterfaceFolha {
	Date getDtInicial() throws Exception;

	Date getDtFinal() throws Exception;

	void incluiDebito(String hist, double valor) throws Exception;

	void incluiCredito(String hist, double valor) throws Exception;
}