package projeto.oficina.DAO;

import projeto.oficina.manutencao.Manutencao;
import java.sql.SQLException;

public class ManutencaoDAO extends ConnectionDAO{

//DAO - Data Access Object

    boolean sucesso = false; //Para saber se funcionou

    public boolean inserirManutencao(Manutencao manutencao) {
        connectToDB();
        String sql = "INSERT INTO Manutencao (problema, status) values(?,?)";

        try { //pst é um comando utilizado para a preparação do comando, usado quando se passa algo por parâmetro
            pst = con.prepareStatement(sql);
            //pst.setInt(1, manutencao.getId());
            pst.setString(1, manutencao.getProblema());
            pst.setString(2, manutencao.getStatus());
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

}
