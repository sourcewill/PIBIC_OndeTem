
<%@page import="modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="dao.DAOPlaceDetailsAux"%>
<%@page import="modelo.PlaceDetailsAux"%>
<%@include file="validaSessao.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head lang="pt-br">

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <link rel="stylesheet" href="css/estilo.css">

        <style> 
            input.largerCheckbox { 
                transform : scale(2); 
            } 
        </style> 

        <title>Listagem de Locais - OndeTem</title>

    </head>
    <body>

        <jsp:include page="navbar.jsp"></jsp:include>        

            <div class="container"> 
                
                <jsp:include page="sessao.jsp"></jsp:include>
                
                <br/>
                <h1>Listagem de Locais 
                    <button type="button" class="btn btn-outline-dark" onclick="window.location.reload()">Atualizar</button>
                </h1>
                <br/>

                <table class="table table-striped">

                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">Selecionar</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Endereço</th>
                            <th scope="col">Gerenciar</th>
                        </tr>
                    </thead>

                    <tbody>
                    <%                        DAOPlaceDetailsAux dao = new DAOPlaceDetailsAux();
                        List<PlaceDetailsAux> locais = dao.buscarTodos();

                        for (PlaceDetailsAux local : locais) {
                    %>
                    <tr>
                        <td><input type="checkbox" class="selecao largerCheckbox ml-4 mt-2" id="<%=local.getPlace_id()%>"></td>
                        <td><%=local.getName()%></td>
                        <td><%=local.getFormatted_address()%></td>
                        <td>
                            <a href="avaliar.jsp?id=<%=local.getPlace_id()%>" class="btn btn-dark" role="button">Avaliar</a>

                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>

            </table>

            <input onclick="if(confirm('Excluir todos os locais selecionados?')) gerenciaSelecionados('excluir.jsp')" class="btn btn-danger btn-block my-3" value="Excluir todos os locais selecionados" type="button" /> 
            <input onclick="if(confirm('Aprovar todos os locais selecionados?')) gerenciaSelecionados('aprovar.jsp')" class="btn btn-success btn-block my-3" value="Aprovar todos os locais selecionados" type="button" />

            <script>

                function gerenciaSelecionados(acao) {
                    var array_checkbox = document.getElementsByClassName('selecao');
                    var selecionados = [];
                    for (var i = 0; i < array_checkbox.length; i++) {
                        if (typeof array_checkbox[i] == 'object') {
                            if (array_checkbox[i].checked) {
                                selecionados.push(array_checkbox[i].id)
                            }
                        }
                    }
                    if (selecionados.length <= 0) {
                        alert("Selecione um pelo menos 1 item!");
                    } else {
                        var url = '';
                        for (var i = 0; i < selecionados.length; i++) {
                            if (i == 0) {
                                url = url.concat(selecionados[i]);
                            } else {
                                url = url.concat('&id='.concat(selecionados[i]));
                            }
                        }
                        window.location.href = acao + "?id=" + url;
                    }
                }

            </script>
            
            

        </div>

        <!-- JavaScript Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


    </body>
</html>
