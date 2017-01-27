package ua.training.library.dao.connection;

public interface AbstractConnection extends AutoCloseable {

    public Object getConnection();
    public void beginTransaction();
    public void rollback();
    public void commit();
    public void close();

}
