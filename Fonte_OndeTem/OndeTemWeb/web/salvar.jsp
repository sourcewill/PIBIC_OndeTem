<%@page import="controle.ControleCadastro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="css/estilo.css"> 

        <title>Cadastro de Local</title>

    </head>

    <body>

        <jsp:include page="navbar.jsp"></jsp:include>

        <%
            String name = request.getParameter("name");
            String formatted_address = request.getParameter("formatted_address");
            String formatted_phone_number = request.getParameter("formatted_phone_number");
            String website = request.getParameter("website");
            Integer typeId = Integer.parseInt(request.getParameter("type"));
            String latitude = request.getParameter("latitude");
            String longitude = request.getParameter("longitude");

            ControleCadastro controle = new ControleCadastro();
            controle.cadastrarLocal(name, formatted_address, formatted_phone_number, website, typeId, latitude, longitude);
        %>

        <div class="container">

            <h1 class="my-3">Cadastro de Local - OndeTem</h1>

            <div class="row">

                <div class="card border-success mb-3" style="width: 25rem;">
                    <div class="card-body text-success">
                        <h5 class="card-title">Sucesso!</h5>
                        <p class="card-text">Agradecemos sua colaboração! O cadastro será avaliado e assim que aprovado, o sistema o recomendará como um ponto de interesse conforme sua categoria.</p>
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
