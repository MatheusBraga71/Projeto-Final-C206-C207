package projeto.oficina.dono;

public class Dono {

    private String nome;
    private String cpf;

    public Dono(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }


    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}