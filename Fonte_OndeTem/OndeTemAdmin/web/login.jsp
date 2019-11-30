<%@page import="modelo.Usuario"%>
<%@page import="controle.ControleUsuario"%>
<%

String login = request.getParameter("usuario");
String senha = request.getParameter("senha");

ControleUsuario controle = new ControleUsuario();
Usuario usuario = controle.validar(login, senha);

if(usuario != null){
    session.setAttribute("usuario", usuario);
    response.sendRedirect("listagem.jsp");
}else{
    response.sendRedirect("index.jsp?erro=validacao&login=" + login);
}

%>