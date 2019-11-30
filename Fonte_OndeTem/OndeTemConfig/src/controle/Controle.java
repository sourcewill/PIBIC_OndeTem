
package controle;

import dao.DAOPlaceTypes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import modelo.PlaceTypes;

/**
 *
 * @author William Rodrigues
 */
public class Controle {
    
    protected final DAOPlaceTypes daoPlaceTypes = new DAOPlaceTypes();
    
        /**
     *
     * Método utilizado para preencher a tabela de tipos e categorias no banco
     * de dados
     *
     * @param caminhoArq caminho referente ao arquivo contendo tipos e
     * categorias. Cada linha no arquivo deve conter o tipo e categoria
     * separados pelo caracter ">".
     */
    public void preencheTabelaCategorias(String caminhoArq) {

        BufferedReader conteudo;
        String linha;
        String separador = ">"; //Caracter usado para separar palavras no arquivo
        String[] palavrasLinha;
        PlaceTypes placeType;

        try {
            conteudo = new BufferedReader(new FileReader(caminhoArq));
            //Enquanto houver linhas no arquivo para serem lidas
            while ((linha = conteudo.readLine()) != null) {
                //Separa cada palavra em um vetor de Strings
                palavrasLinha = linha.split(separador);
                //Preenche os atributos do objeto com as 2 palavras da linha
                placeType = new PlaceTypes();
                placeType.setType(palavrasLinha[0]);
                placeType.setCategory(palavrasLinha[1]);
                placeType.setTraducao(palavrasLinha[2]);
                //Persiste o objeto criado
                daoPlaceTypes.inserir(placeType);
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }
    
}
