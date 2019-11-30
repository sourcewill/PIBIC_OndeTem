package crawler;

import dao.DAOPlaceDetails;
import dao.DAOPlaceReviews;
import dao.DAOPlaceTypes;
import dao.DAORelateDetailsTypes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.util.regex.Pattern;
import modelo.PlaceReviews;
import modelo.PlaceTypes;
import modelo.RelateDetailsTypes;

/**
 *
 * @author William Rodrigues
 *
 * Super classe com atributos e metodos que podem ser herdados por CrawlerWeb e
 * CrawlerAPI
 */
public class Crawler {

    protected final DAOPlaceTypes daoPlaceTypes = new DAOPlaceTypes();
    protected final DAOPlaceDetails daoPlaceDetails = new DAOPlaceDetails();
    protected final DAORelateDetailsTypes daoRelateDetailsTypes = new DAORelateDetailsTypes();
    protected final DAOPlaceReviews daoPlaceReviews = new DAOPlaceReviews();

    /**
     *
     * Método para escrita de texto em determinado arquivo, o texto sempre é
     * escrito em uma nova linha
     *
     * @param caminhoArq caminho do arquivo de texto
     * @param texto texto a ser gravado no arquivo
     */
    public void escreveArq(String caminhoArq, String texto) {
        try {

            FileWriter arq = new FileWriter(caminhoArq, true);//Sempre escreve em uma nova linha
            PrintWriter gravaArq = new PrintWriter(arq);
            gravaArq.println(texto);
            gravaArq.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     *
     * Método booleano utilizado para verificar se determinado texto existe em
     * um arquivo
     *
     * @param caminhoArq caminho do arquivo a ser verificado
     * @param texto string a ser verificada
     * @return boolean true caso exista o texto passado como parametro no
     * arquivo, false caso contrario
     */
    public boolean existeTextoEmArquivo(String caminhoArq, String texto) {

        BufferedReader conteudo;
        String linha;
        PlaceTypes placeType;

        try {
            conteudo = new BufferedReader(new FileReader(caminhoArq));
            //Enquanto houver linhas no arquivo para serem lidas
            while ((linha = conteudo.readLine()) != null) {
                if (linha.equals(texto)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        return false;
    }

    /**
     * Remove todos os objetos RelateDetailsTypes associados a um local
     * informado pelo id
     *
     * @param place_id id do local
     */
    protected void removeRelateDetailsTypes(String place_id) {
        for (RelateDetailsTypes relateDetails : daoRelateDetailsTypes.buscarTodos()) {
            if (relateDetails.getPlace().getPlace_id().equals(place_id)) {
                daoRelateDetailsTypes.remover(relateDetails);
            }
        }
    }

    /**
     * Remove todos os objetos PlaceReviews associados a um local informado pelo
     * id
     *
     * @param place_id id do local
     */
    protected void removePlaceReviews(String place_id) {
        for (PlaceReviews placeReviews : daoPlaceReviews.buscarTodos()) {
            if (placeReviews.getPlace().getPlace_id().equals(place_id)) {
                daoPlaceReviews.remover(placeReviews);
            }
        }
    }

    /**
     * Recebe uma String e substitui todos os caracters acentuados por
     * carcacters sem acento
     *
     * @param str String
     * @return String sem acentos
     */
    protected static String removerAcentos(String str) {

        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

}
