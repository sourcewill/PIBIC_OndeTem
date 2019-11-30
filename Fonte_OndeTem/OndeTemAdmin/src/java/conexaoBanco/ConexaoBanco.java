package conexaoBanco;

import java.io.FileInputStream;
import java.io.IOException;
import static java.util.Objects.isNull;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author William Rodrigues
 * 
 * Classe de Conexão com o Banco de Dados
 */

public class ConexaoBanco {

    private static EntityManagerFactory emf = null;
    private static EntityManager em= null;


    /**
     * Usa um arquivo de configuraão externo além do persistence.xml
     * O arquivo externo deve conter as seguintes propriedades:
     * javax.persistence.jdbc.url= ...
     * javax.persistence.jdbc.user = ... 
     * javax.persistence.jdbc.password = ...
     * javax.persistence.jdbc.driver = ...
     */
    public static void conectar() {

        if (isNull(emf) || isNull(em)) {

            try {
                FileInputStream file = new FileInputStream("C:\\Users\\Windows 7\\Desktop\\PIBIC\\OndeTemAdmin\\config-banco.properties"); //Endereço do arquivo externo de propriedades
                Properties propriedades = new Properties();
                propriedades.load(file);

                emf = Persistence.createEntityManagerFactory("OndeTemAdminJPA", propriedades);
                em = emf.createEntityManager();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public static void desconectar() {
        em.close();
        emf.close();
    }

    public static EntityManagerFactory getEmf() {
        if(isNull(emf)){
            conectar();
        }
        return emf;
    }

    public static EntityManager getEm() {
        if(isNull(em)){
            conectar();
        }
        return em;
    }
}
