<%@page import="controle.ControleAprovar"%>
<%@page import="dao.DAOPlaceDetailsAux"%>
<%@include file="validaSessao.jsp"%>

<%
    
DAOPlaceDetailsAux  dao = new DAOPlaceDetailsAux();
ControleAprovar controle = new ControleAprovar();

String [] vetorParametros = request.getParameterValues("id");

//Aprova objetos recbidos na lista como parametro
for(String valor : vetorParametros){    
    Integer id = Integer.parseInt(valor);
    controle.aprovar(dao.buscarPorId(id));
}

response.sendRedirect("listagem.jsp");

%>