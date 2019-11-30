<%@page import="java.util.List"%>
<%@page import="modelo.PlaceTypes"%>
<%@page import="controle.ControleCadastro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <!-- Leaflet Map -->
        <link rel="stylesheet" href="leaflet/leaflet.css" />
        <script src="leaflet/leaflet.js"></script>

        <style type="text/css">
            #mapid { height: 300px;}
        </style>

        <link rel="stylesheet" href="css/estilo.css">

        <title>Cadastro de Local - OndeTem</title>

    </head>

    <body>

        <jsp:include page="navbar.jsp"></jsp:include>

            <div class="container">

                <br/>
                <h1>Cadastro de Local</h1>
                <br/>
                <form name="formCadastro" method="post" action="salvar.jsp" id="formCadastro" class="needs-validation" novalidate>

                    <label>Nome do Local <b>*</b></label>
                    <input class="form-control" type="text" name="name" value="" placeholder="Nome do Local" required />
                    <div class="invalid-feedback">Por favor, informe o nome do local.</div>                
                    <br/>

                    <label>Tipo <b>*</b></label>
                    <select class="form-control" name="type" required>
                        <option value="">Selecione um tipo</option>
                    <%
                        ControleCadastro controle = new ControleCadastro();
                        List<PlaceTypes> listaTipos = controle.formaListaTipos();
                        for (PlaceTypes tipo : listaTipos) {
                    %>
                    <option value="<%=tipo.getType_id()%>"><%=tipo.getTraducao()%></option>
                    <%
                        }
                    %>
                </select>
                <div class="invalid-feedback">Por favor, selecione o tipo do local.</div>
                <br/>

                <label>Telefone</label>
                <input class="form-control" type="text" name="formatted_phone_number" value="" placeholder="Telefone do Local" />
                <br/>

                <label>Website</label>
                <input class="form-control" type="text" name="website" value="" placeholder="Website do Local"/>
                <br/>

                <label>Endereço <b>*</b></label>
                <input class="form-control" type="text" name="formatted_address" value="" placeholder="Endereço do Local" required />  
                <div class="invalid-feedback">Por favor, informe o endereço do local.</div>
                <br/>

                <label>Indique o local no mapa <b>*</b></label>
                <br/>
                <input onclick="obterPosicao()" class="btn btn-outline-info btn-block my-3" value="Ajustar mapa para minha posição atual" type="button" />

                <div id="mapid" class="rounded-lg"></div>

                <script type="text/javascript">

                    //Instanciando mapa
                    var mymap = L.map('mapid').setView([-23.427320, -51.936958], 13);
                    var marker = L.marker([0, 0]);
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
                <div class="row">
                    <div class="col">
                        <input class="form-control" type="text" id="latitude" name="latitude" placeholder="Indique o local no mapa" value="" hidden required />
                        <div class="invalid-feedback">Por favor, indique o local no mapa.</div>
                    </div>
                    <div class="col">
                        <input class="form-control" type="text" id="longitude" name="longitude" placeholder="Indique o local no mapa" value="" hidden required/>  
                    </div>
                </div>


                <input class="btn btn-primary btn-lg btn-block my-3" type="submit" name="salvar" value="Salvar" />
            </form>

            <script>
                // Validação de formulario (Bootstrap)
                (function () {
                    'use strict';
                    window.addEventListener('load', function () {
                        var forms = document.getElementsByClassName('needs-validation');
                        var validation = Array.prototype.filter.call(forms, function (form) {
                            form.addEventListener('submit', function (event) {
                                if (form.checkValidity() === false) {
                                    event.preventDefault();
                                    event.stopPropagation();
                                }
                                form.classList.add('was-validated');
                            }, false);
                        });
                    }, false);
                })();
            </script>

        </div>

        <!-- JavaScript Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>
</html>
