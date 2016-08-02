package vape.val.liquid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;

import vape.val.liquid.model.Liquid;

/**
 * Created by v.aleksandrenko on 02.08.2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Liquid.db";
    public static final String TABLE_NAME = "FAVLIQUID";
    public static final String NAME = "NAME";
    public static final String NICOTINE_JUICE_ML = "NICOTINE_JUICE_ML";
    public static final String NICOTINE_JUICE_GRAMS = "NICOTINE_JUICE_GRAMS";
    public static final String NICOTINE_JUICE_PERCENT = "NICOTINE_JUICE_PERCENT";
    public static final String PROPYLENEGLYCOL_ML = "PROPYLENEGLYCOL_ML";
    public static final String PROPYLENEGLYCOL_GRAMS = "PROPYLENEGLYCOL_GRAMS";
    public static final String PROPYLENEGLYCOL_PERCENT = "PROPYLENEGLYCOL_PERCENT";
    public static final String VEGETABLEGLYCERIN_ML = "VEGETABLEGLYCERIN_ML";
    public static final String VEGETABLEGLYCERIN_GRAMS = "VEGETABLEGLYCERIN_GRAMS";
    public static final String VEGETABLEGLYCERIN_PERCENT = "VEGETABLEGLYCERIN_PERCENT";
    public static final String WATER_ML = "WATER_ML";
    public static final String WATER_GRAMS = "WATER_GRAMS";
    public static final String WATER_PERCENT = "WATER_PERCENT";
    public static final String FLAVOR_ML_1 = "FLAVOR_ML_1";
    public static final String FLAVOR_GRAMS_1 = "FLAVOR_GRAMS_1";
    public static final String FLAVOR_PERCENT_1 = "FLAVOR_PERCENT_1";
    public static final String FLAVOR_NAME_1 = "FLAVOR_NAME_1";
    public static final String FLAVOR_ML_2 = "FLAVOR_ML_2";
    public static final String FLAVOR_GRAMS_2 = "FLAVOR_GRAMS_2";
    public static final String FLAVOR_PERCENT_2 = "FLAVOR_PERCENT_2";
    public static final String FLAVOR_NAME_2 = "FLAVOR_NAME_2";
    public static final String FLAVOR_ML_3 = "FLAVOR_ML_3";
    public static final String FLAVOR_GRAMS_3 = "FLAVOR_GRAMS_3";
    public static final String FLAVOR_PERCENT_3 = "FLAVOR_PERCENT_3";
    public static final String FLAVOR_NAME_3 = "FLAVOR_NAME_3";
    SQLiteDatabase database;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + NAME + " VARCHAR," + NICOTINE_JUICE_ML + " REAL, " + NICOTINE_JUICE_GRAMS + " REAL, "
                + NICOTINE_JUICE_PERCENT + " REAL, " + PROPYLENEGLYCOL_ML + " REAL, " + PROPYLENEGLYCOL_GRAMS + " REAL, "
                + PROPYLENEGLYCOL_PERCENT + " REAL, " + VEGETABLEGLYCERIN_ML + " REAL, " + VEGETABLEGLYCERIN_GRAMS + " REAL, "
                + VEGETABLEGLYCERIN_PERCENT + " REAL, " + WATER_ML + " REAL, " + WATER_GRAMS + " REAL, " + WATER_PERCENT + " REAL, "
                + FLAVOR_ML_1 + " REAL, " + FLAVOR_GRAMS_1 + " REAL, " + FLAVOR_PERCENT_1 + " REAL, " + FLAVOR_NAME_1 + " VARCHAR, "
                + FLAVOR_ML_2 + " REAL, " + FLAVOR_GRAMS_2 + " REAL, " + FLAVOR_PERCENT_2 + " REAL, " + FLAVOR_NAME_2 + " VARCHAR, "
                + FLAVOR_ML_3 + " REAL, " + FLAVOR_GRAMS_3 + " REAL, " + FLAVOR_PERCENT_3 + " REAL, " + FLAVOR_NAME_3 + " VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertLiquid(Liquid liquid) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, liquid.getName());
        contentValues.put(NICOTINE_JUICE_ML, liquid.getNicotineJuiceMl());
        contentValues.put(NICOTINE_JUICE_GRAMS, liquid.getNicotineJuiceGrams());
        contentValues.put(NICOTINE_JUICE_PERCENT, liquid.getNicotineJuicePercent());
        contentValues.put(PROPYLENEGLYCOL_ML, liquid.getPropyleneGlycolMl());
        contentValues.put(PROPYLENEGLYCOL_GRAMS, liquid.getPropyleneGlycolGrams());
        contentValues.put(PROPYLENEGLYCOL_PERCENT, liquid.getNicotineJuicePercent());
        contentValues.put(VEGETABLEGLYCERIN_ML, liquid.getVegetableGlycerinMl());
        contentValues.put(VEGETABLEGLYCERIN_GRAMS, liquid.getVegetableGlycerinGrams());
        contentValues.put(VEGETABLEGLYCERIN_PERCENT, liquid.getNicotineJuicePercent());
        contentValues.put(WATER_ML, liquid.getWaterMl());
        contentValues.put(WATER_GRAMS, liquid.getWaterGrams());
        contentValues.put(WATER_PERCENT, liquid.getWaterPercent());
        contentValues.put(FLAVOR_ML_1, liquid.getFlavorMl_1());
        contentValues.put(FLAVOR_GRAMS_1, liquid.getFlavorGrams_1());
        contentValues.put(FLAVOR_PERCENT_1, liquid.getFlavorPercent_1());
        contentValues.put(FLAVOR_NAME_1, liquid.getFlavorName_1());
        contentValues.put(FLAVOR_ML_2, liquid.getFlavorMl_2());
        contentValues.put(FLAVOR_GRAMS_2, liquid.getFlavorGrams_2());
        contentValues.put(FLAVOR_PERCENT_2, liquid.getFlavorPercent_2());
        contentValues.put(FLAVOR_NAME_2, liquid.getFlavorName_2());
        contentValues.put(FLAVOR_ML_3, liquid.getFlavorMl_3());
        contentValues.put(FLAVOR_GRAMS_3, liquid.getFlavorGrams_3());
        contentValues.put(FLAVOR_PERCENT_3, liquid.getFlavorPercent_3());
        contentValues.put(FLAVOR_NAME_3, liquid.getFlavorName_3());

        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }

    public void deleteGif(Liquid liquid) {
        database = this.getReadableDatabase();
        database.execSQL("delete from " + TABLE_NAME + " where " + NAME + " = '" + liquid.getName() + "'");
        database.close();
    }

    public ArrayList<Liquid> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Liquid> liquids = new ArrayList<>();
        Liquid liquid;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                liquid = new Liquid();
                liquids.add(liquid);
            }
        }
        cursor.close();
        database.close();
        return liquids;
    }
}
