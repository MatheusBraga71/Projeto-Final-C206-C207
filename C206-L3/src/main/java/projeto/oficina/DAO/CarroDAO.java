package projeto.oficina.DAO;

import projeto.oficina.carros.Carro;
import projeto.oficina.dono.Dono;
import projeto.oficina.funcionario.Mecanico;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarroDAO extends ConnectionDAO{

    //DAO - Data Access Object

    boolean sucesso = false; //Para saber se funcionou

    public boolean inserirCarro(Carro carro) {
        connectToDB();
        String sql = "INSERT INTO Carro (numeroChassi,cor,modelo,Documento_renavam, Dono_cpf,Mecanico_cpf) values(?,?,?,?,?,?)";

        try { //pst é um comando utilizado para a preparação do comando, usado quando se passa algo por parâmetro
            pst = con.prepareStatement(sql);
            pst.setInt(1, carro.getNumeroChassi());
            pst.setString(2, carro.getCor());
            pst.setString(3, carro.getModelo());
            pst.setInt(4, carro.getDocumento_renavam());
            pst.setString(5, carro.getDono_cpf());
            pst.setString(6, carro.getMecanico_cpf());
            pst.execute();
            sucesso = true;
        } catch(SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch(SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean atualizarCorCarro(int numeroChassi, String cor) { // Possivelmente fazer uma sobrecarga de método para alterar cada dado da tabela
        connectToDB();
        String sql = "UPDATE Carro SET cor=? where numeroChassi=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cor);
            pst.setInt(2, numeroChassi);
            pst.execute();
            sucesso = true;

        } catch(SQLException ex) {
            System.out.println("Erro = " +  ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch(SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean atualizarModeloCarro(int numeroChassi, String modelo) {
        connectToDB();
        String sql = "UPDATE Carro SET modelo=? where numeroChassi=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, modelo);
            pst.setInt(2, numeroChassi);
            pst.execute();
            sucesso = true;

        } catch(SQLException ex) {
            System.out.println("Erro = " +  ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch(SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean deletarCarro(int numeroChassi) {
        connectToDB();
        String sql = "DELETE FROM Carro where numeroChassi=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, numeroChassi);
            pst.execute();
            sucesso = true;

        } catch(SQLException ex) {
            System.out.println("Erro = " +  ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch(SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public ArrayList<Carro> buscarCarrosSemFiltro() {
        ArrayList<Carro> listaDeCarros = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Carro INNER JOIN Dono ON Dono.cpf = Carro.Dono_cpf INNER JOIN Mecanico ON Mecanico.cpf = Carro.Mecanico_cpf";

        try { //st é um comando usado quando a função não precisa de uma preparação, função não recebe parâmetro
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de Carros: ");
            while (rs.next()) {
                Carro carroAux = new Carro(rs.getInt("numeroChassi"), rs.getString("cor"), rs.getString("modelo"), rs.getInt("Documento_renavam"), rs.getString("Dono_cpf"), rs.getString("Mecanico_cpf"));
                //Manutencao manAux = new Manutencao(rs.getInt("idManutencao"), rs.getString("status"), rs.getString("problema"));
                Dono donoAux = new Dono(rs.getString("dono.nome"), rs.getString("cpf"));
                Mecanico mecanicoAux = new Mecanico(rs.getString("cpf"), rs.getString("mecanico.nome"));
                System.out.println("Modelo: " + carroAux.getModelo());
                System.out.println("Cor: " + carroAux.getCor());
                System.out.println("Numero do Chassi: " + carroAux.getNumeroChassi());
                System.out.println("Renavam do carro: " + carroAux.getDocumento_renavam());
                System.out.println("Nome do dono: " + donoAux.getNome());
                System.out.println("CPF do Dono: " + carroAux.getDono_cpf());
                System.out.println("Nome do Mecânico responsável: " + mecanicoAux.getNome());
                System.out.println("CPF do Mecânico Responsável: " + carroAux.getMecanico_cpf());
                System.out.println("--------------------------------");
                listaDeCarros.add(carroAux);
            }
            sucesso = true;
        } catch(SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch(SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return listaDeCarros;
    }

    public Carro buscarCarroPorNumeroDoChassi(int numeroChassi) {
        connectToDB();
        Carro carroAux = null;
        String sql = "SELECT * FROM Carro INNER JOIN Dono ON Dono.cpf = Carro.Dono_cpf INNER JOIN Mecanico ON Mecanico.cpf = Carro.Mecanico_cpf AND numeroChassi = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, numeroChassi);
            rs = pst.executeQuery();
            while (rs.next()) {
                String aux = rs.getString("numeroChassi");
                if(aux.isEmpty())
                {
                    sucesso = false;
                } else {
                    carroAux = new Carro(rs.getInt("numeroChassi"), rs.getString("cor"), rs.getString("modelo"), rs.getInt("Documento_renavam"), rs.getString("Dono_cpf"), rs.getString("Mecanico_cpf"));
                    Dono donoAux = new Dono(rs.getString("Dono.nome"), rs.getString("Dono.cpf"));
                    Mecanico mecanicoAux = new Mecanico(rs.getString("Mecanico.cpf"), rs.getString("Mecanico.nome"));

                    //Manutencao manAux = new Manutencao(rs.getInt("idManutencao"), rs.getString("status"), rs.getString("problema"));

                    System.out.println("--------------------------------");
                    System.out.println("Modelo: " + carroAux.getModelo());
                    System.out.println("Cor: " + carroAux.getCor());
                    System.out.println("Numero do Chassi: " + carroAux.getNumeroChassi());
                    System.out.println("Renavam do carro: " + carroAux.getDocumento_renavam());
                    System.out.println("Nome do dono: " + donoAux.getNome());
                    System.out.println("CPF do Dono: " + carroAux.getDono_cpf());
                    System.out.println("Nome do Mecânico Responsável: " + mecanicoAux.getNome());
                    System.out.println("CPF do Mecanico Responsável: " + carroAux.getMecanico_cpf());
                    //System.out.println("Problema do Carro: " + manAux.getProblema());
                    //System.out.println("Status do problema: " + manAux.getStatus());
                    System.out.println("--------------------------------");
                }
            }
            sucesso = true;
        } catch(SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch(SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return carroAux;
    }

    public Dono buscarDadosDoDono(int numeroChassi) {
        connectToDB();
        Dono donoAux = null;
        String sql = "SELECT * FROM Dono INNER JOIN Carro ON Dono.cpf = Carro.Dono_cpf and Carro.numeroChassi = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, numeroChassi);
            rs = pst.executeQuery();
            while (rs.next()) {
                String aux = rs.getString("cpf");
                if(aux.isEmpty())
                {
                    sucesso = false;
                } else {
                    donoAux = new Dono(rs.getString("nome"), rs.getString("cpf"));
                    System.out.println("--------------------------------");
                    System.out.println("Nome: " + donoAux.getNome());
                    System.out.println("CPF: " + donoAux.getCpf());
                    System.out.println("--------------------------------");
                }
            }
            sucesso = true;
        } catch(SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch(SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return donoAux;
    }
}
