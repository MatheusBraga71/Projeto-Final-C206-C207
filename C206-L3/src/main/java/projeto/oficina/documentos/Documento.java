package projeto.oficina.documentos;

public class Documento {

    private int renavam;
    private int anoDoVeiculo;

    public Documento(int renavam, int anoDoVeiculo){
        this.renavam = renavam;
        this.anoDoVeiculo = anoDoVeiculo;
    }

    public int getRenavam() {
        return renavam;
    }

    public int getAnoDoVeiculo() {
        return anoDoVeiculo;
    }
}
