<%@include file="validaSessaoAdm.jsp"%>
<%@page import="controle.ControleUsuario"%>
<%    
    String acao = request.getParameter("acao");
    String login = request.getParameter("login");
    String senha = request.getParameter("senha");
    String permissao = request.getParameter("permissao");
    ControleUsuario controle = new ControleUsuario();
    
    if (acao == null) {    
        
        controle.cadastrarUsuario(login, senha, permissao);
        response.sendRedirect("listagemUsuarios.jsp");
        
    } else if (acao.equals("editar")) {
        
        Integer id = Integer.parseInt(request.getParameter("id"));
        controle.editarUsuario(id, login, senha, permissao);
        response.sendRedirect("listagemUsuarios.jsp");        
    }

%>