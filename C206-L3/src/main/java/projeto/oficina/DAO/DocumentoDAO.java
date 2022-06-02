package projeto.oficina.DAO;

import projeto.oficina.documentos.Documento;

import java.sql.SQLException;

public class DocumentoDAO extends ConnectionDAO {

//DAO - Data Access Object

    boolean sucesso = false; //Para saber se funcionou

    public boolean inserirDocumento(Documento documento) {
        connectToDB();
        String sql = "INSERT INTO Documento (renavam, anoDoVeiculo) values(?,?)";

        try { //pst é um comando utilizado para a preparação do comando, usado quando se passa algo por parâmetro
            pst = con.prepareStatement(sql);
            pst.setInt(1, documento.getRenavam());
            pst.setInt(2, documento.getAnoDoVeiculo());
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

    public boolean deletarDocumento(int renavam) {
        connectToDB();
        String sql = "DELETE FROM Documento where renavam=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, renavam);
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

    public boolean buscaDocExistente(int renavam) {
        connectToDB();
        boolean found = false;
        String sql = "SELECT * FROM Documento WHERE renavam = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, renavam);
            rs = pst.executeQuery();
            while (rs.next()) {
                String aux = rs.getString("renavam");
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
