<%@include file="validaSessaoAdm.jsp"%>
<%@page import="dao.DAOUsuario"%>
<%
    
DAOUsuario  dao = new DAOUsuario();

String [] vetorParametros = request.getParameterValues("id");

//Exclui objetos recebidos na lista como parametro
for(String valor : vetorParametros){
    Integer id = Integer.parseInt(valor);
    if(id==1) continue; //Impede a exclusão do admin inicial
    dao.remover(id);
}

response.sendRedirect("listagemUsuarios.jsp");

%>