
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <link rel="stylesheet" href="leaflet/leaflet.css" />
        <script src="leaflet/leaflet.js"></script>

        <style type="text/css">
            #mapid { height: 500px;
                     width: 500px;}
            </style>



            <title>Map</title>

        </head>

        <body>

            <button onclick="obterPosicao()">Teste</button>

            <div id="mapid"></div>

        <script type="text/javascript">

            //Instanciando mapa
            var mymap = L.map('mapid').setView([-23.427320, -51.936958], 13);
            var marker = L.marker([0,0]);            
            marker.setOpacity(0);
            marker.addTo(mymap);

            //Configura a textura usada no mapa
            L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a>',
                maxZoom: 18,
                id: 'mapbox.streets',
                accessToken: 'pk.eyJ1Ijoib25kZXRlbSIsImEiOiJjanRzdXFrc3kwcjI1NDRwbDk3Z2RsbWdnIn0.8JL_lirzBTeVGGP2-TiYhg'
            }).addTo(mymap);

            //Ao clicar sobre o mapa
            mymap.addEventListener('click', function (e) {
                marker.setLatLng(e.latlng);
                marker.setOpacity(1);
                document.getElementById('latitude').value = e.latlng.lat.toString();
                document.getElementById('longitude').value = e.latlng.lng.toString();
            });

            //Obtem as coordenadas do usuario
            function obterPosicao() {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(mudaVisaoMapa);
                }
            }

            //Modificaa visao do mapa
            function mudaVisaoMapa(posicao) {
                mymap.setView([posicao.coords.latitude, posicao.coords.longitude], 15);
            }

        </script>

        <br/>

        <label>Latitude</label>
        <input type="text" id="latitude"></input>

        <label>Longitude</label>
        <input type="text" id="longitude"></input>  

        <!-- JavaScript Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>
</html>
