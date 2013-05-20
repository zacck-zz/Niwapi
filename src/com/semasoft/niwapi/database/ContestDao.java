package com.semasoft.niwapi.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.Property;

import com.semasoft.niwapi.database.Contest;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table CONTEST.
*/
public class ContestDao extends AbstractDao<Contest, Void> {

    public static final String TABLENAME = "CONTEST";

    /**
     * Properties of entity Contest.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Contest_id = new Property(0, Integer.class, "contest_id", false, "CONTEST_ID");
        public final static Property Contest_media = new Property(1, String.class, "contest_media", false, "CONTEST_MEDIA");
        public final static Property Contest_date_loaded = new Property(2, java.util.Date.class, "contest_date_loaded", false, "CONTEST_DATE_LOADED");
        public final static Property Contest_date_solved = new Property(3, java.util.Date.class, "contest_date_solved", false, "CONTEST_DATE_SOLVED");
    };


    public ContestDao(DaoConfig config) {
        super(config);
    }
    
    public ContestDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'CONTEST' (" + //
                "'CONTEST_ID' INTEGER," + // 0: contest_id
                "'CONTEST_MEDIA' TEXT," + // 1: contest_media
                "'CONTEST_DATE_LOADED' INTEGER," + // 2: contest_date_loaded
                "'CONTEST_DATE_SOLVED' INTEGER);"); // 3: contest_date_solved
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'CONTEST'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Contest entity) {
        stmt.clearBindings();
 
        Integer contest_id = entity.getContest_id();
        if (contest_id != null) {
            stmt.bindLong(1, contest_id);
        }
 
        String contest_media = entity.getContest_media();
        if (contest_media != null) {
            stmt.bindString(2, contest_media);
        }
 
        java.util.Date contest_date_loaded = entity.getContest_date_loaded();
        if (contest_date_loaded != null) {
            stmt.bindLong(3, contest_date_loaded.getTime());
        }
 
        java.util.Date contest_date_solved = entity.getContest_date_solved();
        if (contest_date_solved != null) {
            stmt.bindLong(4, contest_date_solved.getTime());
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public Contest readEntity(Cursor cursor, int offset) {
        Contest entity = new Contest( //
            cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0), // contest_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // contest_media
            cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)), // contest_date_loaded
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)) // contest_date_solved
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Contest entity, int offset) {
        entity.setContest_id(cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0));
        entity.setContest_media(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setContest_date_loaded(cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)));
        entity.setContest_date_solved(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(Contest entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(Contest entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
