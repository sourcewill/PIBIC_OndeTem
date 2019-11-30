<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="pt-br">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="css/estilo.css">

        <title>Inicial - OndeTem</title>
    </head>
    <body>

        <jsp:include page="navbar.jsp"></jsp:include>

        <div class="container">

            <h1 class="my-3">Bem vindo!</h1>

            <div class="row">

                <div class="card mx-3 my-3" style="width: 25rem;">
                    <div class="card-body">
                        <h5 class="card-title">Sobre o Projeto</h5>
                        <p class="card-text">OndeTem é um projeto desenvolvido na Universidade Estadual de Maringá com o objetivo de desenvolver um aplicativo que recomende pontos de interesse em cidades brasileiras para seus usuários.</p>
                    </div>
                </div>

                <div class="card mx-3 my-3" style="width: 25rem;">
                    <div class="card-body">
                        <h5 class="card-title">Cadastre seu Local</h5>
                        <p class="card-text">Colabore para que nosso sistema possa recomendar seu local como um ponto de interesse conforme sua categoria. O cadastro de local é rápido e objetivo!</p>
                        <a class="btn btn-success btn-lg" role="button" href="cadastro.jsp">Cadastrar Local</a>
                    </div>
                </div>

            </div>

        </div>

        <!-- JavaScript Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>

</html>
