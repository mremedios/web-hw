package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.exception.RepositoryException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicRepositoryImpl<T> {
    protected final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    public List<T> findAll(String dataRequest, Object... args) {
        List<T> pars = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(dataRequest)) {
                for (int i = 1; i <= args.length; ++i) {
                    statement.setObject(i, args[i - 1]);
                }
                try (ResultSet resultSet = statement.executeQuery()) {
                    T par;
                    while ((par = toObject(statement.getMetaData(), resultSet)) != null) {
                        pars.add(par);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find TALK.", e);
        }
        return pars;
    }

    public T find(String dataRequest, Object... args) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(dataRequest)) {
                for (int i = 1; i <= args.length; ++i) {
                    statement.setObject(i, args[i - 1]);
                }
                try (ResultSet resultSet = statement.executeQuery()) {
                    return toObject(statement.getMetaData(), resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find object.", e);
        }
    }

    protected abstract T toObject(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException;

}
