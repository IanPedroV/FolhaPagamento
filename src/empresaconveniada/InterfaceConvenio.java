package empresaconveniada;

import folhadepagamento.InterfaceFolha;

public interface InterfaceConvenio {
	void processaContrato(String id, InterfaceFolha f) throws Exception;
}