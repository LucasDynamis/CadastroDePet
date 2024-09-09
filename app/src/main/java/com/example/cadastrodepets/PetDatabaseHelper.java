package com.example.cadastrodepets;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PetDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pets.db"; // Nome do banco de dados
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "animais";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_IDADE = "idade";
    public static final String COLUMN_PESO = "peso";
    public static final String COLUMN_RACA = "raca";
    public static final String COLUMN_COR = "cor";
    public static final String COLUMN_NOME_DONO = "nome_dono";
    public static final String COLUMN_TELEFONE_DONO = "telefone_dono";

    public PetDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PETS_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOME + " TEXT, "
                + COLUMN_IDADE + " INTEGER, "
                + COLUMN_PESO + " REAL, "
                + COLUMN_RACA + " TEXT, "
                + COLUMN_COR + " TEXT, "
                + COLUMN_NOME_DONO + " TEXT, "
                + COLUMN_TELEFONE_DONO + " TEXT)";
        db.execSQL(CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
