package projeto.oficina.carros;

public class Carro {

    private int numeroChassi;
    private String cor;
    private String modelo;
    private int Documento_renavam;
    private String Dono_cpf;
    private String Mecanico_cpf;

    public Carro(int numeroChassi, String cor, String modelo,int Documento_renavam, String Dono_cpf, String Mecanico_cpf){
        this.numeroChassi = numeroChassi;
        this.cor = cor;
        this.modelo = modelo;
        this.Documento_renavam = Documento_renavam;
        this.Dono_cpf = Dono_cpf;
        this.Mecanico_cpf = Mecanico_cpf;
    }

    public int getNumeroChassi() {
        return numeroChassi;
    }

    public String getCor() {
        return cor;
    }

    public String getModelo() {
        return modelo;
    }

    public int getDocumento_renavam() {
        return Documento_renavam;
    }

    public String getDono_cpf() {
        return Dono_cpf;
    }

    public String getMecanico_cpf() {
        return Mecanico_cpf;
    }

}

