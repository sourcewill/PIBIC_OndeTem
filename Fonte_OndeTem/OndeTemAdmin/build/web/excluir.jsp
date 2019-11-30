<%@include file="validaSessao.jsp"%>
<%@page import="dao.DAOPlaceDetailsAux"%>
<%
    
DAOPlaceDetailsAux  dao = new DAOPlaceDetailsAux();

String [] vetorParametros = request.getParameterValues("id");

//Exclui objetos recebidos na lista como parametro
for(String valor : vetorParametros){
    Integer id = Integer.parseInt(valor);
    dao.remover(id);
}

response.sendRedirect("listagem.jsp");

%>