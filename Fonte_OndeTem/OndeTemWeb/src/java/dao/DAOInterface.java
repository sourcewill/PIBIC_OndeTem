package dao;

import java.util.List;

public interface DAOInterface<T> {

    public T inserir(T object);
    public T alterar(T object);
    public boolean remover(T object);
    public boolean remover(Class<T> clazz,Integer id);
    public List<T> buscarTodos(Class<T> clazz);
    public T buscarPorId(Class<T> clazz,Integer id);
}