package vape.val.liquid.database.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import vape.val.liquid.model.coil.Coil;
import vape.val.liquid.model.liquid.Liquid;

/**
 * Created by v.aleksandrenko on 26.08.2016.
 */
public class CoilDAO extends BaseDaoImpl<Coil, String> {

    public CoilDAO(ConnectionSource connectionSource, Class<Coil> dataClass)
            throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Coil> getAllLCoil() throws SQLException{
        return this.queryForAll();
    }
}
