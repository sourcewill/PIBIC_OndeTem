package crawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import modelo.PlaceDetails;
import modelo.PlaceReviews;
import modelo.PlaceTypes;
import modelo.RelateDetailsTypes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * Classe que realiza e trata as requisições feitas atravez da API do Google
 *
 * @author William Rodrigues
 */
public class CrawlerAPI extends Crawler {

    /**
     *
     * Método que efetua uma requisição URL e retorna sua resposta no formato Json
     * 
     * @param requisicaoUrl String contendo a URL de requisição
     * @return String contendo a resposta no formato Json
     */
    public String requisicaoURL(String requisicaoUrl) {

        URL url = null;
        try {
            url = new URL(requisicaoUrl);
        } catch (MalformedURLException ex) {
            System.err.println("Erro: " + ex.getMessage());
        }

        try {
            HttpURLConnection conexao;
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            int codigoResposta = conexao.getResponseCode();
            System.out.println("Código de Resposta: " + codigoResposta + "\n");
            BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            StringBuilder resposta = new StringBuilder();
            while ((linha = in.readLine()) != null) {
                resposta.append(linha);
            }
            in.close();
            return resposta.toString();

        } catch (IOException ex) {
            System.err.println("Erro: " + ex.getMessage());
            return null;
        }

    }

    /**
     * 
     * @param respostaJson String resultante de uma requisição URL à API do Google
     */
    public void lerJsonNearbySearch(String respostaJson) {

        try {
            JSONObject jsonObj = new JSONObject(respostaJson);
            JSONArray results = jsonObj.getJSONArray("results");
            int numResultados = results.length();
            JSONObject place;
            StringBuilder requisicao;
            String palavrasLocal;

            //Para cada local encontrado
            for (int i = 0; i < numResultados; i++) {

                place = results.getJSONObject(i);
                String place_id = place.getString("place_id");

                //Formando URL de requisição (Place Details)
                requisicao = new StringBuilder();
                requisicao.append("https://maps.googleapis.com/maps/api/place/details/json?placeid=")
                        .append(place_id)
                        .append("&fields=name,formatted_address,formatted_phone_number,geometry,icon,website,type,review,rating&key=")
                        .append(Argumentos.APIkey);

                //Efetuando requisição (Place Details)
                System.out.println("Efetuando requisição URL (Place Details)");
                respostaJson = requisicaoURL(requisicao.toString());

                try {
                    System.out.println("Dormindo por 24h...");
                    Thread.sleep(86400000);
                } catch (InterruptedException ex) {
                    System.err.println("Erro: " + ex.getMessage());
                }

                //Validando palavras do local
                palavrasLocal = formaPalavrasLocal(respostaJson);
                if (validaLocal(palavrasLocal, "palavras_proibias.txt")) {
                    //Lendo Json resultante (Place Datails)
                    lerJsonPlaceDetails(respostaJson, place_id);
                }

            }

        } catch (JSONException ex) {
            System.err.println("Erro: " + ex.getMessage());
        }

    }

    /**
     *
     * @param respostaJson String resultante de uma requisição URL à API do Google
     * @param place_id ID do local
     */
    public void lerJsonPlaceDetails(String respostaJson, String place_id) {

        //Verifica se o local ja consta no Banoc de Dados
        PlaceDetails placeDetails = daoPlaceDetails.buscarPorId(place_id);

        if (placeDetails == null) { //Novo local
            placeDetails = new PlaceDetails();
            placeDetails.setPlace_id(place_id);
        } else { //Local repitido
            removeRelateDetailsTypes(place_id);
            removePlaceReviews(place_id);
        }

        try {
            //Criando e persistindo objeto PlaceDetails
            JSONObject jsonObj = new JSONObject(respostaJson);
            JSONObject result = jsonObj.getJSONObject("result");
            String name = result.getString("name");
            placeDetails.setName(name);

            String formatted_address = result.getString("formatted_address");
            placeDetails.setFormatted_address(formatted_address);

            String formatted_phone_number = result.getString("formatted_phone_number");
            placeDetails.setFormatted_phone_number(formatted_phone_number);

            JSONObject geometry = result.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            Double lat = location.getDouble("lat");
            placeDetails.setLatitude(lat.toString());

            Double lng = location.getDouble("lng");
            placeDetails.setLongitude(lng.toString());

            String icon = result.getString("icon");
            placeDetails.setIcon(icon);

            String website = result.getString("website");
            placeDetails.setWebsite(website);

            Double rating = result.getDouble("rating");
            placeDetails.setRating(rating.floatValue());

            placeDetails = daoPlaceDetails.inserir(placeDetails);

            //Criando e Persisitindo Objetos RelateDetailsTypes para cada tipo do local 
            JSONArray tipos = result.getJSONArray("types");
            int numTipos = tipos.length();

            //Cria um vetor de Strings com cada tipo
            String[] types = new String[numTipos];
            for (int i = 0; i < numTipos; i++) {
                types[i] = tipos.getString(i);
            }
            //Percorre o vetor persistindo os objetos
            RelateDetailsTypes relateDetailsTypes;
            for (String type : types) {
                relateDetailsTypes = new RelateDetailsTypes();
                PlaceTypes placeType = daoPlaceTypes.buscaPorType(type);
                if (placeType == null) {
                    continue;
                }
                relateDetailsTypes.setPlace(placeDetails);
                relateDetailsTypes.setType(placeType);
                daoRelateDetailsTypes.inserir(relateDetailsTypes);
            }

            //Criando e Persisitindo Objetos PlaceReviews para cada review do local 
            PlaceReviews placeReview;
            JSONArray reviews = result.getJSONArray("reviews");
            JSONObject review;
            int numReviews = reviews.length();

            for (int i = 0; i < numReviews; i++) {
                placeReview = new PlaceReviews();
                review = reviews.getJSONObject(i);

                placeReview.setPlace(placeDetails);

                String author_name = review.getString("author_name");
                placeReview.setAuthor_name(author_name);

                String text = review.getString("text");
                placeReview.setText(text);

                String language = review.getString("language");
                placeReview.setLanguage(language);

                rating = review.getDouble("rating");
                placeReview.setOverall_rating(Float.parseFloat(rating.toString()));

                daoPlaceReviews.inserir(placeReview);

            }

        } catch (JSONException ex) {
            System.err.println("Erro: " + ex.getMessage());
        }

    }

    

    /**
     *
     * @param respostaJson json de resposta da requisição URL da API
     * @return String com as palavras relacionadas ao local
     */
    public String formaPalavrasLocal(String respostaJson) {
        StringBuilder palavrasLocal = new StringBuilder();
        JSONObject jsonObj;
        try {
            //Adiciona o nome do local a String
            jsonObj = new JSONObject(respostaJson);
            JSONObject result = jsonObj.getJSONObject("result");
            String name = result.getString("name");
            palavrasLocal.append(name);

            //Adiciona os tipos do local a String
            JSONArray tipos = result.getJSONArray("types");
            int numTipos = tipos.length();
            for (int i = 0; i < numTipos; i++) {
                palavrasLocal.append(tipos.getString(i));
            }

        } catch (JSONException ex) {
            System.err.println("Erro: " + ex.getMessage());
        }
        return palavrasLocal.toString();
    }

    /**
     *
     * @param palavrasLocal palavras contidas no Json de resposta da API
     * referentes a um determinado local
     * @param caminhoArq caminho referente ao arquivo com palavras proibidas.
     * Cada linha no arquivo deve conter palavras que não podem aparecer
     * simultaneamente em um local, devem estar separadas por um espaço.
     * @return true caso o local nao apresente mais de uma palavra contida em
     * uma linha do arquivo. false caso ocorra mais de uma palavra da linha no
     * local.
     */
    public boolean validaLocal(String palavrasLocal, String caminhoArq) {

        //Converte todos os carcteres para minúsclo
        palavrasLocal = palavrasLocal.toLowerCase();
        BufferedReader conteudo;
        String linha;
        //Caracter usado para eparar palavras no arquivo
        String separador = " ";
        int contador;
        String[] palavrasLinha;

        try {
            conteudo = new BufferedReader(new FileReader(caminhoArq));
            //Enquanto houver linhas no arquivo para serem lidas
            while ((linha = conteudo.readLine()) != null) {
                contador = 0;
                //Converte todos os carcteres para minúsclo
                linha = linha.toLowerCase();
                //Separa cada palavra em um vetor de Strings
                palavrasLinha = linha.split(separador);
                for (String palavra : palavrasLinha) {
                    //Se a palavra está contida no conjunto de palavras do local
                    if (palavrasLocal.contains(palavra)) {
                        contador++;
                    }
                    //Se mais de uma palavra estiver contida retorna false
                    if (contador > 1) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
            return false;
        }

        return true;
    }
}
