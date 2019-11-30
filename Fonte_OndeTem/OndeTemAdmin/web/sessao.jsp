<%@page import="modelo.Usuario"%>
<%
    Usuario u = (Usuario) session.getAttribute("usuario");
    if (u != null) {
%>

<br/>
<div class="container text-right">

    <span class="border border-dark rounded shadow py-1 px-2 mx-1"><%=u.getLogin()%> [<%=u.getPermissao()%>]</span>
    <a href="sair.jsp" class="btn btn-sm btn-dark" role="button">Sair</a>

</div>

<%
    }
%>