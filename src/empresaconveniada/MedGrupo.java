package empresaconveniada;

import folhadepagamento.InterfaceFolha;

public class MedGrupo implements InterfaceConvenio {
	
	public void processaContrato(String id, InterfaceFolha f) throws Exception {
		if (id.equals("1"))
			f.incluiDebito("Convênio MedGrupo", 30);
		else
			throw new Exception();

	}
}
