<%@page import="modelo.Usuario"%>
<%
    Usuario user = (Usuario) session.getAttribute("usuario");
    if (user == null) {
        response.sendRedirect("index.jsp");
    }else if(!user.getPermissao().equalsIgnoreCase("administrador")){
        response.sendRedirect("listagem.jsp");
    }
%>