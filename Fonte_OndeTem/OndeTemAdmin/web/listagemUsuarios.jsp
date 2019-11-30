
<%@page import="modelo.Usuario"%>
<%@page import="dao.DAOUsuario"%>
<%@page import="java.util.List"%>
<%@include file="validaSessaoAdm.jsp"%>
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

        <title>Listagem de Usuários - OndeTem</title>

    </head>
    <body>

        <jsp:include page="navbar.jsp"></jsp:include>

            <div class="container">
                
                <jsp:include page="sessao.jsp"></jsp:include>

                <br/>
                <h1>Listagem de Usuários</h1>
                <br/>

                <table class="table table-striped">

                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">Selecionar</th>
                            <th scope="col">Login</th>
                            <th scope="col">Permissão</th>
                            <th scope="col">Gerenciar</th>
                        </tr>
                    </thead>

                    <tbody>
                    <%
                        DAOUsuario dao = new DAOUsuario();
                        List<Usuario> usuarios = dao.buscarTodos();

                        for (Usuario usuario : usuarios) {
                    %>
                    <tr>
                        <td><input type="checkbox" class="selecao largerCheckbox ml-4 mt-2" id="<%=usuario.getId()%>"></td>
                        <td><%=usuario.getLogin()%></td>
                        <td><%=usuario.getPermissao()%></td>
                        <td>
                            <a href="editarUsuario.jsp?id=<%=usuario.getId()%>" class="btn btn-dark" role="button">Editar</a>

                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>

            </table>
                
            <input onclick="if(confirm('Excluir todos os usuários selecionados?')) gerenciaSelecionados('excluirUsuario.jsp')" class="btn btn-danger btn-block my-3" value="Excluir todos os usuários selecionados" type="button" />            
            <a href="cadastroUsuario.jsp" class="btn btn-success btn-block my-3" role="button">Cadastrar novo usuário</a>

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
