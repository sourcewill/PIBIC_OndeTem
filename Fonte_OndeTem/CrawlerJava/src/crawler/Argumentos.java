package crawler;

/**
 *
 * @author William Rodrigues
 * 
 * Classe utilizada para manipulação dos argumentos de entrada
 */

public class Argumentos {

    public static String opcaoCrawler; //Opção sobre qual Crawler executar (WEB ou API)
    
    //Argumentos usados no Crawler WEB
    public static String idCidadeKekanto;
    
    //Argumentos usados no Crawler API
    public static String APIkey; //API key do Google
    public static String latitude;
    public static String longitude;
    public static String raio;
}
