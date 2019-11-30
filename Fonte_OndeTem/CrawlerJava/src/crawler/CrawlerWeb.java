package crawler;

import java.io.IOException;
import java.util.Scanner;
import modelo.PlaceDetails;
import modelo.PlaceReviews;
import modelo.PlaceTypes;
import modelo.RelateDetailsTypes;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * Classe que realiza o Crawler de locais sobre a pagina Kekanto
 * 
 * @author William Rodrigues
 */
public class CrawlerWeb extends Crawler {

    public void executar(String idCidade, Integer numPagina) {

        int numResultados;
        int numComentarios;

        Float notaAutor;

        String link;

        Document conteudoPagina;
        Element results;
        Elements cards;
        Element card;
        Element infoCard;
        Element phone;
        Element adress;
        Element rating;
        Elements reviewBox;
        Element review;

        String nomeLocal;
        String enderecoLocal;
        String telefoneLocal;
        String latitude;
        String longitude;
        String tipo;
        String classificacao;
        String texto;
        String autor;
        String linkAutor;
        String notaString;

        PlaceDetails placeDetails;
        RelateDetailsTypes relateDetailsTypes;
        PlaceReviews placeReviews;

        try {

            while (true) {

                //Formando URL de busca no site Kekanto
                link = "https://kekanto.com.br/newsearch/page:" + numPagina + "?&search=&cidade_id=" + idCidade;

                //Obtendo conteudo da pagina
                conteudoPagina = Jsoup.connect(link).get();
                results = conteudoPagina.getElementById("bizes-content-list");

                //Verifica se a página contem resultados
                if (results == null) {
                    System.out.println("Fim dos resultados, a página atual não contem mais locais.");
                    break;
                }

                cards = results.getElementsByClass("biz-card-content");
                numResultados = cards.size();
                System.out.println("Obtendo resultados na página [ " + numPagina + " ]");
                System.out.println("Numero de resultados: " + numResultados);
                System.out.println("");

                //Intera sobre cada card (resultado) na pagina
                for (int i = 0; i < numResultados; i++) {

                    //Obtendo Url do local
                    card = cards.get(i);
                    infoCard = card.getElementsByClass("biz-card-info").first();
                    link = infoCard.getElementsByTag("a").first().attr("href");
                    System.out.println("Pagina do Local: " + link);

                    //Verificando se o local ja consta no Banco de Dados
                    placeDetails = daoPlaceDetails.buscarPorId(link);

                    if (placeDetails == null) { //Novo local
                        placeDetails = new PlaceDetails();
                        placeDetails.setPlace_id(link); //O ID do local será o proprio url dele no site Kekanto
                    } else { //Local repitido
                        removeRelateDetailsTypes(link);
                        removePlaceReviews(link);
                    }
                    
                    placeDetails.setUrl(link);

                    System.out.println("Obtendo infomrções do local...");

                    //Obtendo conteudo da pagina do local
                    conteudoPagina = Jsoup.connect(link).get();

                    //Obtendo nome do local
                    nomeLocal = conteudoPagina.getElementsByClass("biz-name").first().text();
                    System.out.println("Nome: " + nomeLocal);
                    placeDetails.setName(nomeLocal);

                    //Obtendo endereço do local
                    adress = conteudoPagina.getElementsByClass("biz-address").first();
                    enderecoLocal = adress.getElementsByTag("span").first().text(); //Obtem rua e numero
                    enderecoLocal = enderecoLocal + ", " + adress.getElementsByTag("a").get(1).text(); //Obtem zona
                    enderecoLocal = enderecoLocal + " - " + adress.getElementsByTag("a").get(2).text(); //Obtem cidade
                    //enderecoLocal = enderecoLocal + ", " + adress.getElementsByTag("a").get(3).text(); //Obtem estado
                    System.out.println("Endereço: " + enderecoLocal);
                    placeDetails.setFormatted_address(enderecoLocal);

                    //Obtendo telefone do local
                    phone = conteudoPagina.getElementById("full_phone");
                    if (phone != null) {
                        telefoneLocal = phone.text();
                        System.out.println("Telefone: " + telefoneLocal);
                        placeDetails.setFormatted_phone_number(telefoneLocal);
                    } else {
                        System.out.println("Telefone não cadastrado para o local");
                    }

                    //Obtendo latitude do local
                    latitude = conteudoPagina.getElementsByAttribute("property").get(2).attr("content");
                    System.out.println("Latitude: " + latitude);
                    placeDetails.setLatitude(latitude);

                    //Obtendo latitude do local
                    longitude = conteudoPagina.getElementsByAttribute("property").get(3).attr("content");
                    System.out.println("Longitude: " + longitude);
                    placeDetails.setLongitude(longitude);

                    //Obtendo classificação do local
                    rating = conteudoPagina.getElementsByClass("biz-rating-value").first();
                    if (rating != null) {
                        classificacao = rating.getElementsByTag("strong").first().text();
                        System.out.println("Classificação: " + classificacao);
                        System.out.println("Classificação detalhada: " + rating.text());
                        placeDetails.setRating(Float.parseFloat(classificacao));
                    } else {
                        System.out.println("Classificação não cadastrada para o local");
                    }

                    daoPlaceDetails.inserir(placeDetails);

                    //Obtendo tipo do local
                    tipo = conteudoPagina.getElementById("breadcrumb").getElementsByTag("li").get(2).getElementsByTag("span").first().text();
                    System.out.println("Tipo: " + tipo);
                    relateDetailsTypes = new RelateDetailsTypes();
                    
                    PlaceTypes placeType = daoPlaceTypes.buscaPorType(removerAcentos(tipo));
                    if (placeType == null){ //Caso o tipo ainda nao esteja no arquivo de categorizacao
                        if(!existeTextoEmArquivo("tipos_nao_categorizados.txt", tipo)){ //Se esse tipo ainda nao foi gravado
                            escreveArq("tipos_nao_categorizados.txt", tipo); //Grava tipo no arquivo de tipos nao categorizados
                        }
                        
                        continue; //Pula a intereção atual, impedindo a persistencia do objeto
                    }
                    
                    relateDetailsTypes.setPlace(placeDetails);
                    relateDetailsTypes.setType(placeType);
                    daoRelateDetailsTypes.inserir(relateDetailsTypes);

                    //Obtendo comentarios
                    System.out.println("\nComentarios sobre o local:\n");

                    reviewBox = conteudoPagina.getElementsByClass("box-timeline layout-biz js-box-timeline");
                    numComentarios = reviewBox.size();

                    for (int j = 0; j < numComentarios; j++) {

                        placeReviews = new PlaceReviews();
                        placeReviews.setPlace(placeDetails);

                        //Obtendo autor do comentario
                        autor = reviewBox.get(j).getElementsByClass("box-tm-text-header-title text-truncate ").text();
                        System.out.println("Autor: " + autor);
                        placeReviews.setAuthor_name(autor);

                        //Obtendo link do autor
                        linkAutor = reviewBox.get(j).getElementsByClass("box-tm-user-photo relative").first()
                                .getElementsByTag("a").first().attr("href");
                        System.out.println("Link do autor: " + linkAutor);
                        placeReviews.setAuthor_url(linkAutor);

                        //Obtendo texto do comentario
                        review = reviewBox.get(j).getElementsByClass("box-tm-main color-888").first();
                        texto = review.text();
                        //Removendo informações desnecessárias do texto
                        int pos = texto.indexOf("•");
                        if (pos > 0) {
                            texto = texto.substring(0, pos);
                            texto = texto.replace("Gostei Comentar ", "");
                        }
                        //Caso o comentári oseja muito longo
                        if (texto.length() > 999) {
                            texto = texto.substring(0, 999);
                        }
                        System.out.println("Texto: " + texto);
                        placeReviews.setText(texto);

                        //Obtendo nota que o autor do comentario deixou
                        Element elementNota = review.getElementsByClass("box-tm-rating left ").first();
                        if(elementNota == null){
                            elementNota = review.getElementsByClass("box-tm-rating left  review_star-translate ").first();
                        }                        
                        notaString = elementNota.getElementsByTag("meta").first().attr("content");
                        
                        System.out.println("Nota: " + notaString);
                        notaAutor = Float.parseFloat(notaString);
                        placeReviews.setOverall_rating(notaAutor);

                        daoPlaceReviews.inserir(placeReviews);

                        System.out.println("");
                    }

                    System.out.println("---------------------------------------------------------------------\n");
                }

                numPagina++; //Incrementa o numero da página para cessar a próxima página de resultados

            }

        } catch (IOException e) {
            System.err.println("ERRO! " + e.getMessage());
            System.out.println("Ultima pagina visitada: " + numPagina);
            
            Scanner scanner = new Scanner(System.in);
            System.out.println("Pressione uma tecla para retomar o Crawler a partir da página atual...");
            scanner.nextLine();
            executar(idCidade, numPagina); //Executa novamente, a partir da pagina em que parou
        }

    }

}
