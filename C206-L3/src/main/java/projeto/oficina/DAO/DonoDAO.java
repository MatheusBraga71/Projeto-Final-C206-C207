package projeto.oficina.DAO;

import projeto.oficina.dono.Dono;

import java.sql.SQLException;
import java.util.ArrayList;

public class DonoDAO extends ConnectionDAO {

    //DAO - Data Access Object

    boolean sucesso = false; //Para saber se funcionou

    public boolean inserirDono(Dono dono) {
        connectToDB();
        String sql = "INSERT INTO Dono (nome,cpf) values(?,?)";

        try { //pst é um comando utilizado para a preparação do comando, usado quando se passa algo por parâmetro
            pst = con.prepareStatement(sql);
            pst.setString(1, dono.getNome());
            pst.setString(2, dono.getCpf());
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

    public boolean atualizarCPFDono(String cpf, String ncpf) {
        connectToDB();
        String sql = "UPDATE Dono SET cpf=? where cpf=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, ncpf);
            pst.setString(2, cpf);
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

    public boolean atualizarNomeDono(String cpf, String nome) {
        connectToDB();
        String sql = "UPDATE Dono SET nome=? where cpf=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, cpf);
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

    public boolean deletarDono(String cpf) {
        connectToDB();
        String sql = "DELETE FROM Dono where cpf=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
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

    public ArrayList<Dono> buscarDonoSemFiltro() {
        ArrayList<Dono> listaDeDonos = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Dono";

        try { //st é um comando usado quando a função não precisa de uma preparação, função não recebe parâmetro
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de donos: ");
            while (rs.next()) {
                Dono donoAux = new Dono(rs.getString("Nome"), rs.getString("cpf"));
                System.out.println("nome = " + donoAux.getNome());
                System.out.println("cpf = " + donoAux.getCpf());
                System.out.println("--------------------------------");
                listaDeDonos.add(donoAux);
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
        return listaDeDonos;
    }
    public Dono buscarDonoPorCPF(String cpf) {
        connectToDB();
        Dono donoAux = null;
        String sql = "SELECT * FROM Dono WHERE cpf = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
            rs = pst.executeQuery();
            while (rs.next()) {
                String aux = rs.getString("nome");
                if(aux.isEmpty())
                {
                    sucesso = false;
                } else {
                    donoAux = new Dono(rs.getString("nome"), rs.getString("cpf"));
                    System.out.println("nome = " + donoAux.getNome());
                    System.out.println("marca = " + donoAux.getCpf());
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
