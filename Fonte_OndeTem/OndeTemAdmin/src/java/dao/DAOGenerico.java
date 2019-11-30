package dao;

import conexaoBanco.ConexaoBanco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.Modifier;
import java.util.List;

import static java.lang.String.format;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static java.util.Objects.isNull;


public class DAOGenerico<T> implements DAOInterface<T> {

    private static final Logger log = LoggerFactory.getLogger(DAOGenerico.class);

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public DAOGenerico() {
        emf = ConexaoBanco.getEmf();
        em = ConexaoBanco.getEm();
    }

    @Override
    public T inserir(T object) {
        log.info(format("Inserindo registro %s na classe %s", object.toString(), object.getClass().getSimpleName()));
        if (isNull(object)) {
            String strError = format("Tentativa de insersão na classe %s de registro nulo", object.getClass().getSimpleName());
            log.error(strError);
        }
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
        return object;
    }

    @Override
    public T alterar(T object) {
        try {
            Class<?> clazz = object.getClass();
            Method methodGetId = clazz.getDeclaredMethod("getId");
            Integer id = (Integer) methodGetId.invoke(object);
            Object objetoAntigo = em.find(clazz, id);
            List<Field> privateFields = new ArrayList<>();
            Field[] allFields = clazz.getDeclaredFields();
            for (Field field : allFields) {
                if (Modifier.isPrivate(field.getModifiers())) {
                    boolean accessible = field.isAccessible();
                    field.setAccessible(true);
                    log.info("Valor antigo: "+field.get(objetoAntigo).toString()+"; Valor novo: "+field.get(object).toString());
                    field.set(objetoAntigo,field.get(object));
                    field.setAccessible(accessible);
                }
            }
            return inserir((T) objetoAntigo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean remover(T object) {
        log.info(format("Removendo registro %s na classe %s", object.toString(), object.getClass().getSimpleName()));
        if (isNull(object)) {
            String strError = format("Tentativa de exclusão na classe %s de registro nulo", object.getClass().getSimpleName());
            log.error(strError);
        }
        em.getTransaction().begin();
        em.remove(object);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean remover(Class<T> clazz, Integer id) {
        T object = buscarPorId(clazz, id);
        if (isNull(object)) {
            String strError = format("Não foi possível remover registro da classe %s, pelo id %d, cause: Id não encontrado", clazz.getSimpleName(), id);
            log.error(strError);
        }
        return remover(object);
    }

    @Override
    public T buscarPorId(Class<T> clazz, Integer id) {
        log.info(format("Buscando registro da classe %s pelo id %s", clazz.getSimpleName(), id));
        return em.find(clazz, id);
    }

    @Override
    public List<T> buscarTodos(Class<T> clazz) {
        log.info(format("Buscando todos os registros da classe %s", clazz.getSimpleName()));
        em.getTransaction().begin();
        List<T> lista = em.createQuery("select p from " + clazz.getSimpleName() + " p").getResultList();
        em.getTransaction().commit();
        return lista;
    }
    
}
