<%@page import="modelo.PlaceTypes"%>
<%@page import="java.util.List"%>
<%@page import="modelo.PlaceDetailsAux"%>
<%@page import="dao.DAOPlaceDetailsAux"%>
<%@page import="controle.ControleAprovar"%>
<%@include file="validaSessao.jsp"%>
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

        <title>Avaliar Local - OndeTem</title>

    </head>

    <body>

        <%            
            Integer id = Integer.parseInt(request.getParameter("id"));
            DAOPlaceDetailsAux dao = new DAOPlaceDetailsAux();
            PlaceDetailsAux place = dao.buscarPorId(id);
        %>

        <jsp:include page="navbar.jsp"></jsp:include>

            <div class="container">

            <jsp:include page="sessao.jsp"></jsp:include>

                <br/>
                <h1>Avaliar Local</h1>
                <br/>
                <form name="formCadastro" method="post" action="salvar.jsp" id="formCadastro" class="needs-validation" novalidate>

                    <label>Nome do Local</label>
                    <input class="form-control" type="text" name="name" value="<%=place.getName()%>" placeholder="Nome do Local" required />
                <div class="invalid-feedback">Por favor, informe o nome do local.</div>
                <br/>

                <label>Tipo</label>
                <select class="form-control" name="type" required>
                    <%
                        ControleAprovar controle = new ControleAprovar();
                        List<PlaceTypes> listaTipos = controle.formaListaTipos();
                        for (PlaceTypes placeType : listaTipos) {
                    %>
                    <option value="<%=placeType.getType_id()%>" <% if (placeType.getType_id().equals(place.getTypeId()))out.println("selected");%>>
                        <%=placeType.getTraducao()%></option>
                    <%
                        }
                    %>
                </select>
                <div class="invalid-feedback">Por favor, selecione o tipo do local.</div>
                <br/>

                <label>Telefone</label>
                <input class="form-control" type="text" name="formatted_phone_number" value="<%=place.getFormatted_phone_number()%>" placeholder="Telefone do Local" />
                <br/>

                <label>Website</label>
                <input class="form-control" type="text" name="website" value="<%=place.getWebsite()%>" placeholder="Website do Local"/>
                <br/>

                <label>Endereço</label>
                <input class="form-control" type="text" name="formatted_address" value="<%=place.getFormatted_address()%>" placeholder="Endereço do Local" required />  
                <div class="invalid-feedback">Por favor, informe o endereço do local.</div>
                <br/>

                <label>Local no mapa</label>
                <br/>

                <div id="mapid" class="rounded-lg"></div>

                <script type="text/javascript">

                    //Instanciando mapa
                    var mymap = L.map('mapid').setView([<%=place.getLatitude()%>, <%=place.getLongitude()%>], 15);
                    var marker = L.marker([<%=place.getLatitude()%>, <%=place.getLongitude()%>]);
                    marker.setOpacity(1);
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
                        <input class="form-control" type="text" id="latitude" name="latitude" placeholder="Indique o local no mapa" value="<%=place.getLatitude()%>" hidden required />
                        <div class="invalid-feedback">Por favor, indique o local no mapa.</div>
                    </div>
                    <div class="col">
                        <input class="form-control" type="text" id="longitude" name="longitude" placeholder="Indique o local no mapa" value="<%=place.getLongitude()%>" hidden required/>  
                    </div>
                    <div class="col">
                        <input class="form-control" type="text" id="place_id" name="place_id" value="<%=place.getPlace_id()%>" hidden required/>
                    </div>
                </div>

                <a href="excluir.jsp?id=<%=place.getPlace_id()%>" class="btn btn-danger btn-block my-3" role="button">Excluir Local</a>
                <input class="btn btn-success btn-block my-3" type="submit" name="salvar" value="Salvar e aprovar" />
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
