package folhadepagamento;

import controlefuncionarios.Funcionario;
import controlefuncionarios.FuncionarioMensalista;
import controleventos.*;
import excecoes.FolhaException;
import util.DataUtil;

class GerenteFolhaDePagamento {

    public void registrarAtraso(FuncionarioMensalista funcionarioMensalista, double horasDeAtraso) {
        try {
            EventoAtraso eventoAtraso = new EventoAtraso(DataUtil.getCurrentDate(), horasDeAtraso);
            funcionarioMensalista.processaEvento(eventoAtraso);
            funcionarioMensalista.registraEvento(eventoAtraso);
        } catch (FolhaException e) {
            e.printStackTrace();
        }
    }

    public void registrarComissao(FuncionarioMensalista funcionarioMensalista, double comisssao) {
        try {
            EventoComissao eventoComissao = new EventoComissao(DataUtil.getCurrentDate(), comisssao);
            funcionarioMensalista.processaEvento(eventoComissao);
            funcionarioMensalista.registraEvento(eventoComissao);
        } catch (FolhaException e) {
            e.printStackTrace();
        }
    }

    public void reajustarFolha(Funcionario funcionario, double valor) {
        try {
            EventoReajuste eventoReajuste = new EventoReajuste(DataUtil.getCurrentDate(), valor);
            funcionario.processaEvento(eventoReajuste);
            funcionario.registraEvento(eventoReajuste);
        } catch (FolhaException e) {
            e.printStackTrace();
        }
    }

    public void registrarFaltas(FuncionarioMensalista funcionarioMensalista) {
        try {
            EventoFalta eventoFalta = new EventoFalta(DataUtil.getCurrentDate());
            funcionarioMensalista.processaEvento(eventoFalta);
            funcionarioMensalista.registraEvento(eventoFalta);
        } catch (FolhaException e) {
            e.printStackTrace();
        }
    }

    public void recindir(Funcionario funcionario, int motivo, boolean deuAvisoPrevio) {
        try {
            EventoRescisao eventoRescisao = new EventoRescisao(DataUtil.getCurrentDate(), motivo, deuAvisoPrevio);
            funcionario.processaEvento(eventoRescisao);
            funcionario.registraEvento(eventoRescisao);
        } catch (FolhaException e) {
            e.printStackTrace();
        }
    }


}
