<%@include file="validaSessaoAdm.jsp"%>
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

        <link rel="stylesheet" href="css/estilo.css">

        <title>Cadastro de Usuario - OndeTem</title>

    </head>

    <body>

        <jsp:include page="navbar.jsp"></jsp:include>

        <div class="container">
            
            <jsp:include page="sessao.jsp"></jsp:include>

            <br/>
            <h1>Cadastro de Usuario</h1>
            <br/>

            <form name="formCadastro" method="post" action="salvarUsuario.jsp" id="formCadastro" class="needs-validation" novalidate>

                <label>Login do usuário</label>
                <input class="form-control" type="text" name="login" value="" placeholder="Login do usuário" required />
                <div class="invalid-feedback">Por favor, informe o nome do usuário.</div>                
                <br/>

                <label>Permissão</label>
                <select class="form-control" name="permissao" required>
                    <option value="">Selecione uma permissão</option>
                    <option value="Administrador">Administrador</option>
                    <option value="Usuario">Usuário</option>                    
                </select>
                <div class="invalid-feedback">Por favor, selecione a permissão do usuário.</div>
                <br/>

                <label>Senha</label>
                <input class="form-control" type="password" name="senha" value="" placeholder="Senha" required />
                <div class="invalid-feedback">Por favor, informe a senha do usuário.</div>                
                <br/>

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

    </body>
</html>
