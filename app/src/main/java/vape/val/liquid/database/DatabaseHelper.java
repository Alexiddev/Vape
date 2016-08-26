package vape.val.liquid.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import vape.val.liquid.database.dao.CoilDAO;
import vape.val.liquid.database.dao.LiquidDAO;
import vape.val.liquid.model.coil.Coil;
import vape.val.liquid.model.liquid.Liquid;

/**
 * Created by v.aleksandrenko on 26.08.2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME ="Liquid.db";
    private static final int DATABASE_VERSION = 3;

    private LiquidDAO liquidDao = null;
    private CoilDAO coilDAO = null;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource){
        try
        {
            TableUtils.createTable(connectionSource, Liquid.class);
           TableUtils.createTable(connectionSource, Coil.class);
        }
        catch (SQLException e){
            Log.e(TAG, "error creating DB " + DATABASE_NAME);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer,
                          int newVer){
        try{

            TableUtils.dropTable(connectionSource, Liquid.class, true);
            TableUtils.dropTable(connectionSource, Coil.class, true);
            onCreate(db, connectionSource);
        }
        catch (SQLException e){
            Log.e(TAG,"error upgrading db "+DATABASE_NAME+"from ver "+oldVer);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


    public LiquidDAO getLiquidDAO() throws SQLException, java.sql.SQLException {
        if(liquidDao == null){
            liquidDao = new LiquidDAO(getConnectionSource(), Liquid.class);
        }
        return liquidDao;
    }

    public CoilDAO getCoilDAO() throws SQLException, java.sql.SQLException {
        if(coilDAO == null){
            coilDAO = new CoilDAO(getConnectionSource(), Coil.class);
        }
        return coilDAO;
    }


    @Override
    public void close(){
        super.close();
        liquidDao = null;
        coilDAO = null;
    }
}
