package projeto.oficina.DAO;

import projeto.oficina.funcionario.Mecanico;

import java.sql.SQLException;

public class MecanicoDAO extends ConnectionDAO{

    //DAO - Data Access Object

    boolean sucesso = false; //Para saber se funcionou

    public boolean inserirMecanico(Mecanico mecanico) {
        connectToDB();
        String sql = "INSERT INTO Mecanico (cpf,nome) values(?,?)";

        try { //pst é um comando utilizado para a preparação do comando, usado quando se passa algo por parâmetro
            pst = con.prepareStatement(sql);
            pst.setString(1, mecanico.getCpf());
            pst.setString(2, mecanico.getNome());
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

    public boolean atualizarNomeMecanico(String cpf, String nome) {
        connectToDB();
        String sql = "UPDATE Mecanico SET nome=? where cpf=?";

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

    public boolean buscaMecanicoExistente(String cpf) {
        connectToDB();
        boolean found = false;
        String sql = "SELECT * FROM Mecanico WHERE cpf = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
            rs = pst.executeQuery();
            while (rs.next()) {
                String aux = rs.getString("cpf");
                if(aux.isEmpty())
                {
                    sucesso = false;
                    found = false;
                }
                else{
                    found = true;
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
        return found;
    }
}
