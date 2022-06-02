package projeto.oficina;

import projeto.oficina.DAO.*;
import projeto.oficina.carros.Carro;
import projeto.oficina.documentos.Documento;
import projeto.oficina.dono.Dono;
import projeto.oficina.funcionario.Mecanico;
import projeto.oficina.manutencao.Manutencao;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // Variável para inicialização do Scanner

        MecanicoDAO mDAO = new MecanicoDAO(); // Instanciação de um objeto mecânico

        System.out.println("Bem vindo ao menu! ");
        System.out.println("Digite o seu nome: ");
        String nomeAux = sc.nextLine();
        System.out.println("Digite o seu CPF: ");
        String cpfAux = sc.nextLine();

        Mecanico m1 = new Mecanico(cpfAux, nomeAux);

        if(mDAO.buscaMecanicoExistente(cpfAux) == true){
            System.out.println("Bem vindo de volta! ");
        } else{
            mDAO.inserirMecanico(m1);
        }

        boolean flag = true;
        int contador = 0; // variável auxiliar para a contagem de manutenções (id auto_increment)

        CarroDAO cDAO = new CarroDAO();
        DocumentoDAO docDAO = new DocumentoDAO();
        DonoDAO dDAO= new DonoDAO();
        ManutencaoDAO manDAO = new ManutencaoDAO();
        CarroHasManutencaoDAO cmDAO = new CarroHasManutencaoDAO();

        while(flag){
            int escolha1;
            System.out.println("1 - Inserir um novo carro");
            System.out.println("2 - Realizar uma busca");
            System.out.println("3 - Inserir uma manutenção");
            System.out.println("4 - Remover um carro");
            System.out.println("5 - Atualizar um dado");
            System.out.println("6 - Realizar teste");
            System.out.println("7 - Sair");
            System.out.println("Digite a opção desejada:");
            escolha1 = sc.nextInt();

            switch (escolha1){
                case 1:
                    //CarroDAO cDAO = new CarroDAO();
                    System.out.println("Insira o Número do Chassi do carro: ");
                    int chassi = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Insira a cor do carro: ");
                    String cor = sc.nextLine();
                    System.out.println("Insira o modelo do carro: ");
                    String modelo = sc.nextLine();

                    //DocumentoDAO docDAO = new DocumentoDAO();
                    System.out.println("Insira o renavam do carro: ");
                    int renavamAux = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Insira o ano do veículo: ");
                    int anoAux = sc.nextInt();
                    sc.nextLine();
                    Documento docAux = new Documento(renavamAux, anoAux);

                    System.out.println("Novo dono? sim = 1 | nao = 2");
                    int escolhaDono = sc.nextInt();
                    sc.nextLine();

                    switch (escolhaDono){
                        case 1:
                            //DonoDAO dDAO= new DonoDAO();
                            System.out.println("Insira o nome do dono do carro: ");
                            String nomeNovoDonoAux = sc.nextLine();
                            System.out.println("Insira o cpf do dono: ");
                            String cpfNovoDonoAux = sc.nextLine();

                            Dono donoAux = new Dono(nomeNovoDonoAux, cpfNovoDonoAux);
                            dDAO.inserirDono(donoAux);

                            Carro carroAux = new Carro(chassi, cor, modelo, renavamAux, cpfNovoDonoAux, m1.getCpf());

                            if(docDAO.buscaDocExistente(renavamAux) == false) {
                                docDAO.inserirDocumento(docAux);
                                cDAO.inserirCarro(carroAux);
                            } else{
                                System.out.println("Renavam já cadastrado!");
                            }

                            break;

                        case 2:
                            System.out.println("Insira o cpf do dono: ");
                            String cpfDonoAux = sc.nextLine();

                            Carro carroAux2 = new Carro(chassi, cor, modelo, renavamAux, cpfDonoAux, m1.getCpf());

                            if(docDAO.buscaDocExistente(renavamAux) == false) {
                                docDAO.inserirDocumento(docAux);
                                cDAO.inserirCarro(carroAux2);
                            } else{
                                System.out.println("Renavam já cadastrado!");
                            }

                            break;

                        default:
                            System.out.println("Opção Inválida!");
                            break;
                    }

                    break;

                case 2:
                    int escolhaBusca;
                    int buscaAux;
                    System.out.println("1 - Realizar uma busca geral (todos os dados de todos os carros)");
                    System.out.println("2 - Realizar uma busca personalizada (todos os dados de um carro específico)");
                    System.out.println("3 - Realizar a busca dos dados do dono de um carro");
                    System.out.println("4 - Realizar a busca de todos os problemas");
                    System.out.println("5 - Realizar a busca dos problemas de um carro específico");
                    System.out.println("Digite a sua escolha: ");
                    escolhaBusca = sc.nextInt();
                    sc.nextLine();

                    switch (escolhaBusca){
                        case 1:
                            cDAO.buscarCarrosSemFiltro();
                            cmDAO.buscarManutencaoSemFiltro();
                            break;
                        case 2:
                            System.out.println("Digite o número do chassi do carro desejado: ");
                            buscaAux = sc.nextInt();
                            sc.nextLine();
                            cDAO.buscarCarroPorNumeroDoChassi(buscaAux);
                            cmDAO.buscarManutencoesDoCarro(buscaAux);
                            break;
                        case 3:
                            System.out.println("Digite o número do chassi do carro desejado: ");
                            buscaAux = sc.nextInt();
                            sc.nextLine();
                            cDAO.buscarDadosDoDono(buscaAux);
                            break;
                        case 4:
                            cmDAO.buscarManutencaoSemFiltro();
                            break;
                        case 5:
                            System.out.println("Digite o numero do Chassi do carro desejado: ");
                            int manChassi = sc.nextInt();
                            sc.nextLine();

                            cmDAO.buscarManutencoesDoCarro(manChassi);
                            break;
                        default:
                            System.out.println("Opção Inválida!");
                            break;
                    }
                    break;

                case 3:

                    System.out.println("Qual carro necessita de manutenção? (Insira o Número do Chassi)");
                    int numChassiAux = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Insira o problema: ");
                    String problemaAux = sc.nextLine();
                    System.out.println("Insira o status da manutenção do carro: ");
                    String statusAux = sc.nextLine();
                    contador++;

                    Manutencao manAux = new Manutencao(contador, statusAux, problemaAux);
                    manDAO.inserirManutencao(manAux);

                    cmDAO.inserirManutencao(numChassiAux, manAux);

                    break;

                case 4:
                    System.out.println("Digite o número do chassi do carro desejado: ");
                    int deleteAux = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Digite o renavam do carro desejado: ");
                    int docDeleteAux = sc.nextInt();
                    sc.nextLine();

                    docDAO.deletarDocumento(docDeleteAux);
                    cDAO.deletarCarro(deleteAux);

                    break;

                case 5:
                    int escolhaAtualizacao;
                    System.out.println("1 - Atualizar Modelo de um carro");
                    System.out.println("2 - Atualizar Cor de um carro");
                    System.out.println("3 - Atualizar Nome do Dono do carro");
                    System.out.println("4 - Atualizar CPF do Dono do carro (Para correção de entrada errada)");
                    System.out.println("5 - Atualizar nome do Mecânico");
                    System.out.println("Digite a sua escolha: ");
                    escolhaAtualizacao = sc.nextInt();
                    sc.nextLine();
                    int attCarroAux; // Variável auxiliar para a atualizar dados do carro
                    String attCpfAux; // Variável auxiliar para a atualizar dados do Dono
                    String attMecanicoAux; // Variável auxiliar para atualizar nome do Mecânico

                    switch(escolhaAtualizacao){
                        case 1:
                            System.out.println("Insira o número do Chassi do carro desejado: ");
                            attCarroAux = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Para qual modelo será alterado?");
                            String modeloAux = sc.nextLine();

                            cDAO.atualizarModeloCarro(attCarroAux, modeloAux);

                            break;

                        case 2:
                            System.out.println("Insira o número do Chassi do carro desejado: ");
                            attCarroAux = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Para qual cor será alterado?");
                            String corAux = sc.nextLine();

                            cDAO.atualizarCorCarro(attCarroAux, corAux);

                            break;

                        case 3:
                            System.out.println("Insira o cpf do Dono: ");
                            attCpfAux = sc.nextLine();

                            System.out.println("Para qual nome será alterado? ");
                            String attNomeAux = sc.nextLine();

                            dDAO.atualizarNomeDono(attCpfAux, attNomeAux);

                            break;

                        case 4:
                            System.out.println("Insira o cpf do Dono: ");
                            attCpfAux = sc.nextLine();

                            System.out.println("Para qual cpf será alterado? ");
                            String nCPF = sc.nextLine(); // Novo CPF a ser inserido

                            dDAO.atualizarNomeDono(attCpfAux, nCPF);

                            break;

                        case 5:
                            System.out.println("Insira o CPF do Mecânico");
                            attMecanicoAux = sc.nextLine();

                            System.out.println("Para qual nome será alterado? ");
                            String nomeMecAux = sc.nextLine();

                            mDAO.atualizarNomeMecanico(attMecanicoAux, nomeMecAux);

                            break;

                        default:
                            System.out.println("Opção Inválida!");
                            break;

                    }
                    break;

                case 6:
                    int escolhaTeste;

                    System.out.println("Qual o Id da manutenção testada?");
                    int idTeste = sc.nextInt();
                    sc.nextLine();

                    System.out.println("O problema foi solucionado? (sim = 1 | nao = 2)");
                    escolhaTeste = sc.nextInt();
                    sc.nextLine();

                    switch (escolhaTeste) {
                        case 1:
                            cmDAO.atualizarStatusManutencao(idTeste, "Concluído!");
                            break;

                        case 2:
                            cmDAO.atualizarStatusManutencao(idTeste, "Verificar novamente!");
                            break;

                        default:
                            System.out.println("Opção Inválida!");
                            break;
                    }

                    break;

                case 7:
                    System.out.println("Você saiu!");
                    flag = false;
                    break;

                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        }
        sc.close();
    }
}
