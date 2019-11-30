package principal;

import conexaoBanco.ConexaoBanco;
import crawler.CrawlerAPI;
import crawler.Argumentos;
import crawler.Crawler;
import crawler.CrawlerWeb;
import dao.DAOPlaceTypes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author William Rodrigues
 */
public class Principal {

    /**
     *
     * @param args
     *
     * WEB Para executar o Crawler Web, sendo necessário os seguintes parametros
     * na sequencia: numPagina | idCidadeKekanto 
     * O parametro numPagina refere a pagina que o Crawler deve iniciar
     * O parametro idCidadeKekanto refere ao
     * ID da cidade no siteKekanto, caso este parametro nao seja fornecido, será
     * utilizado como padrão o ID da cidade de Maringá
     *
     * API Para executar o Crawler API Google, sendo necessários os seguintes
     * parametros na segquencia: APIKey | latitude | longitude | raio
     */
    public static void main(String[] args) {

        ConexaoBanco.conectar();

        Crawler crawler = new Crawler();
        DAOPlaceTypes dAOPlaceTypes = new DAOPlaceTypes();

        Argumentos.opcaoCrawler = args[0].toLowerCase();

        switch (Argumentos.opcaoCrawler) {

            case "web":         
                
                if (args.length<2) {
                    Argumentos.idCidadeKekanto = "259377"; //Id da cidade de Maringá
                }else{
                    Argumentos.idCidadeKekanto = args[1];
                }
                executaCrawlerWeb();
                break;

            case "api":
                
                Argumentos.APIkey = args[1];
                Argumentos.latitude = args[2];
                Argumentos.longitude = args[3];
                Argumentos.raio = args[4];
                executaCrawlerAPI();
                break;

            default:
                System.err.println("Erro, o argumento de entrada para opção do Crawler é inválido."
                        + "\nWEB - Para executar o Crawler Web, sendo necessários os seguintes parametros na sequencia:"
                        + "\nnumPagina | idCidadeKekanto"
                        + "\nAPI - Para executar o Crawler API Google, sendo necessários os seguintes parametros na sequencia:"
                        + "\nAPIKey | latitude | longitude | raio");
                break;
        }

    }

    private static void executaCrawlerWeb() {

        CrawlerWeb crawlerWeb = new CrawlerWeb();
        crawlerWeb.executar(Argumentos.idCidadeKekanto, 1); //Por padrão, inicia na página 1

        ConexaoBanco.desconectar();
    }

    private static void executaCrawlerAPI() {

        CrawlerAPI crawlerAPI = new CrawlerAPI();

        //Formando URL de requisição (Nearby Search)
        StringBuilder requisicao = new StringBuilder();
        requisicao.append("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=")
                .append(Argumentos.latitude)
                .append(",")
                .append(Argumentos.longitude)
                .append("&radius=")
                .append(Argumentos.raio)
                .append("&key=")
                .append(Argumentos.APIkey);

        //Efetuando requisição (Nearby Search)
        System.out.println("Efetuando requisição URL (Nearby Search)");
        String respostaJson = crawlerAPI.requisicaoURL(requisicao.toString());

        try {
            System.out.println("Dormindo por 24h...");
            Thread.sleep(86400000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Lendo Json resultante (Nearby Search)
        crawlerAPI.lerJsonNearbySearch(respostaJson);

        /*
            String respostaTeste = "{   \"html_attributions\" : [],   \"result\" : {      \"formatted_address\" : \"Av. Colombo, 5790 - Zona 7, Maringá - PR, 87020-900, Brazil\",      \"formatted_phone_number\" : \"(44) 3011-4040\",      \"geometry\" : {         \"location\" : {            \"lat\" : -23.404717,            \"lng\" : -51.9399913         },         \"viewport\" : {            \"northeast\" : {               \"lat\" : -23.39668915,               \"lng\" : -51.9265176            },            \"southwest\" : {               \"lat\" : -23.41551935,               \"lng\" : -51.95418800000002            }         }      },      \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/school-71.png\",      \"name\" : \"State University of Maringá\",      \"rating\" : 4.4,      \"reviews\" : [         {            \"author_name\" : \"A Google User\",            \"language\" : \"en\",            \"rating\" : 5,            \"relative_time_description\" : \"2 years ago\",            \"text\" : \"Beautiful place! A lot o trees, now under reform to expand the street. \"         },         {            \"author_name\" : \"A Google User\",            \"language\" : \"en\",            \"rating\" : 5,            \"relative_time_description\" : \"a year ago\",            \"text\" : \"Ola pow esi cyti e masa\"         },         {            \"author_name\" : \"A Google User\",            \"language\" : \"pt\",            \"rating\" : 5,            \"relative_time_description\" : \"2 months ago\",            \"text\" : \"Instituição de excelência que consegue ser uma das principais do país mesmo com o orçamento muito reduzido. Há falta de funcionários (professores e demais servidores) e de financiamento para a manutenção dos campi, principalmente a finalização de blocos iniciados.\"         },         {            \"author_name\" : \"A Google User\",            \"language\" : \"pt\",            \"rating\" : 1,            \"relative_time_description\" : \"4 weeks ago\",            \"text\" : \"Antro petista\"         },         {            \"author_name\" : \"A Google User\",            \"language\" : \"pt\",            \"rating\" : 5,            \"relative_time_description\" : \"8 months ago\",            \"text\" : \"A Universidade Estadual de Maringá está localizada na terceira maior cidade do Paraná e uma das que tem melhores condições de vida que é Maringá. A UEM é orgulho para todos os paranaenses é um dos melhores e mais conceituados centros de ensino superior do sul do Brasil. É um dos melhores campos universitários do Paraná.\"         }      ],      \"types\" : [ \"university\", \"point_of_interest\", \"establishment\" ],      \"website\" : \"http://www.uem.br/\"   },   \"status\" : \"OK\"}";
            crowlerAPI.lerJsonPlaceDetails(respostaTeste, "ChIJ-VgjmtPW7JQRHlx9q0oqp5U");
         */
        ConexaoBanco.desconectar();
    }

}
