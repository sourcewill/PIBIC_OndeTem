<%@page import="controle.ControleAprovar"%>
<%@include file="validaSessao.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Integer place_id = Integer.parseInt(request.getParameter("place_id"));
    String name = request.getParameter("name");
    String formatted_address = request.getParameter("formatted_address");
    String formatted_phone_number = request.getParameter("formatted_phone_number");
    String website = request.getParameter("website");
    Integer typeId = Integer.parseInt(request.getParameter("type"));
    String latitude = request.getParameter("latitude");
    String longitude = request.getParameter("longitude");

    //Altera o objeto a partir dos parametros recebidos
    ControleAprovar controle = new ControleAprovar();
    controle.editarLocal(place_id, name, formatted_address, formatted_phone_number, website, typeId, latitude, longitude);

    //Aprova o objeto, já alterado
    response.sendRedirect("aprovar.jsp?id=" + place_id.toString());
%>

