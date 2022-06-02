package projeto.oficina.funcionario;

public class Mecanico {

    private String cpf;
    private String nome;

    public Mecanico(String cpf, String nome){
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

}
