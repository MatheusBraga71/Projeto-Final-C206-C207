package projeto.oficina.DAO;

import projeto.oficina.carros.Carro;
import projeto.oficina.manutencao.Manutencao;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarroHasManutencaoDAO extends ConnectionDAO{

    //DAO - Data Access Object

    boolean sucesso = false; //Para saber se funcionou

    public boolean inserirManutencao(int numChassi, Manutencao manutencao) {
        connectToDB();

        String sql = "INSERT INTO Carro_Has_Manutencao (Carro_numeroChassi, Manutencao_idManutencao) values(?,?)";

        try { //pst é um comando utilizado para a preparação do comando, usado quando se passa algo por parâmetro
            pst = con.prepareStatement(sql);
            pst.setInt(1, numChassi);
            pst.setInt(2, manutencao.getId());
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

    public boolean atualizarStatusManutencao(int id, String status) {
        connectToDB();
        String sql = "UPDATE Manutencao SET status=? where idManutencao=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, status);
            pst.setInt(2, id);
            //pst.setInt(3, numChassi);
            pst.execute();
            sucesso = true;
            System.out.println("Status da manutenção alterado para: " + status);

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

    public ArrayList<Manutencao> buscarManutencaoSemFiltro() {
        ArrayList<Manutencao> listaDeManutencao = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Manutencao INNER JOIN Carro_Has_Manutencao ON Manutencao.idManutencao = Manutencao_idManutencao INNER JOIN Carro ON carro.numeroChassi = Carro_numeroChassi";

        try { //st é um comando usado quando a função não precisa de uma preparação, função não recebe parâmetro
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de Manutenções: ");
            while (rs.next()) {
                Manutencao manAux = new Manutencao(rs.getInt("idManutencao"),rs.getString("status"), rs.getString("problema"));
                Carro carroAux = new Carro(rs.getInt("numeroChassi"), rs.getString("cor"), rs.getString("modelo"), rs.getInt("Documento_renavam"), rs.getString("Dono_cpf"), rs.getString("Mecanico_cpf"));
                System.out.println("Modelo do carro: " + carroAux.getModelo());
                System.out.println("Cor: " + carroAux.getCor());
                System.out.println("Número do chassi do carro: " + carroAux.getNumeroChassi());
                System.out.println("ID: " + manAux.getId());
                System.out.println("Problema: " + manAux.getProblema());
                System.out.println("Status: " + manAux.getStatus());
                System.out.println("--------------------------------");
                listaDeManutencao.add(manAux);
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
        return listaDeManutencao;
    }
    public Manutencao buscarManutencoesDoCarro( int chassi) {
        connectToDB();
        Manutencao manAux = null;
        String sql = "SELECT * FROM Carro_Has_Manutencao INNER JOIN Manutencao ON Manutencao_idManutencao = Manutencao.idManutencao INNER JOIN Carro ON Carro_numeroChassi = Carro.numeroChassi and numeroChassi=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, chassi);
            rs = pst.executeQuery();
            System.out.println("Lista de Manutenção do carro: ");
            while (rs.next()) {
                String aux = rs.getString("numeroChassi");
                if(aux.isEmpty())
                {
                    sucesso = false;
                } else {
                    manAux = new Manutencao(rs.getInt("idManutencao"),rs.getString("status"), rs.getString("problema"));
                    System.out.println("Problema: " + manAux.getProblema());
                    System.out.println("Status: " + manAux.getStatus());
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
        return manAux;
    }
}
