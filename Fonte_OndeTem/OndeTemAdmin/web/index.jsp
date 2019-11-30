<%@page import="modelo.Usuario"%>
<%@page import="dao.DAOUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    if (user != null) {
        session.invalidate();
    }
%>

<!DOCTYPE html>
<html>
    <head lang="pt-br">

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <link rel="stylesheet" href="css/estilo.css">

        <title>Login - OndeTem</title>

    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="index.jsp">
                    <img src="imagens/ondetem512x512.png" width="33" height="33" class="d-inline-block align-top" alt="">
                    Onde Tem [Admin]
                </a>

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSite">
                    <span class="navbar-toggler-icon"></span>
                </button>                

                </div>
            </div>
        </nav>

        <div class="container">

            <br/>
            <h1>Login</h1>
            <br/>

            <%
                String erro = request.getParameter("erro");
                String login = "";
                if (erro != null) {
                    login = request.getParameter("login");
            %>
            <div class="alert alert-danger" role="alert">
                Usuário ou senha inválidos!
            </div>
            <%
                }
            %>

            <form name="formCadastro" method="post" action="login.jsp" id="formCadastro" class="needs-validation" novalidate>

                <label>Usuário</label>
                <input class="form-control" type="text" name="usuario" value="<%=login%>" placeholder="Usuário" required />
                <div class="invalid-feedback">Por favor, informe o login do usuário.</div>
                <br/>

                <label>Senha</label>
                <input class="form-control" type="password" name="senha" value="" placeholder="Senha" required />
                <div class="invalid-feedback">Por favor, informe a senha do usuário.</div>
                <br/>

                <input class="btn btn-primary btn-lg btn-block my-3" type="submit" name="entrar" value="Entrar" />

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
