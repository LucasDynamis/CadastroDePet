package com.example.cadastrodepets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {
    private SQLiteDatabase db;
    private PetDatabaseHelper dbHelper;

    public AnimalDAO(Context context) {
        dbHelper = new PetDatabaseHelper(context);
    }

    public void abrir() {
        db = dbHelper.getWritableDatabase();
    }

    public void fechar() {
        dbHelper.close();
    }

    public void adicionarAnimal(Animal animal) {
        ContentValues values = new ContentValues();
        values.put(PetDatabaseHelper.COLUMN_NOME, animal.getNome());
        values.put(PetDatabaseHelper.COLUMN_IDADE, animal.getIdade());
        values.put(PetDatabaseHelper.COLUMN_PESO, animal.getPeso());
        values.put(PetDatabaseHelper.COLUMN_RACA, animal.getRaca());
        values.put(PetDatabaseHelper.COLUMN_COR, animal.getCor());
        values.put(PetDatabaseHelper.COLUMN_NOME_DONO, animal.getNomeDono());
        values.put(PetDatabaseHelper.COLUMN_TELEFONE_DONO, animal.getTelefoneDono());

        db.insert(PetDatabaseHelper.TABLE_NAME, null, values);
    }

    public List<Animal> obterTodosAnimais() {
        List<Animal> animais = new ArrayList<>();

        Cursor cursor = db.query(PetDatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_ID);
                int nomeIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_NOME);
                int idadeIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_IDADE);
                int pesoIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_PESO);
                int racaIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_RACA);
                int corIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_COR);
                int nomeDonoIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_NOME_DONO);
                int telefoneDonoIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_TELEFONE_DONO);

                if (idIndex != -1 && nomeIndex != -1 && idadeIndex != -1 && pesoIndex != -1 && racaIndex != -1 && corIndex != -1 && nomeDonoIndex != -1 && telefoneDonoIndex != -1) {
                    Animal animal = new Animal(
                            cursor.getInt(idIndex),
                            cursor.getString(nomeIndex),
                            cursor.getInt(idadeIndex),
                            cursor.getDouble(pesoIndex),
                            cursor.getString(racaIndex),
                            cursor.getString(corIndex),
                            cursor.getString(nomeDonoIndex),
                            cursor.getString(telefoneDonoIndex)
                    );
                    animais.add(animal);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return animais;
    }

    public void atualizarAnimal(Animal animal) {
        ContentValues values = new ContentValues();
        values.put(PetDatabaseHelper.COLUMN_NOME, animal.getNome());
        values.put(PetDatabaseHelper.COLUMN_IDADE, animal.getIdade());
        values.put(PetDatabaseHelper.COLUMN_PESO, animal.getPeso());
        values.put(PetDatabaseHelper.COLUMN_RACA, animal.getRaca());
        values.put(PetDatabaseHelper.COLUMN_COR, animal.getCor());
        values.put(PetDatabaseHelper.COLUMN_NOME_DONO, animal.getNomeDono());
        values.put(PetDatabaseHelper.COLUMN_TELEFONE_DONO, animal.getTelefoneDono());

        db.update(PetDatabaseHelper.TABLE_NAME, values, PetDatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(animal.getId())});
    }

    public void deletarAnimal(int id) {
        db.delete(PetDatabaseHelper.TABLE_NAME, PetDatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Animal obterAnimalPorId(int id) {
        Cursor cursor = db.query(PetDatabaseHelper.TABLE_NAME, null, PetDatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_ID);
            int nomeIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_NOME);
            int idadeIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_IDADE);
            int pesoIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_PESO);
            int racaIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_RACA);
            int corIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_COR);
            int nomeDonoIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_NOME_DONO);
            int telefoneDonoIndex = cursor.getColumnIndex(PetDatabaseHelper.COLUMN_TELEFONE_DONO);

            if (idIndex != -1 && nomeIndex != -1 && idadeIndex != -1 && pesoIndex != -1 && racaIndex != -1 && corIndex != -1 && nomeDonoIndex != -1 && telefoneDonoIndex != -1) {
                Animal animal = new Animal(
                        cursor.getInt(idIndex),
                        cursor.getString(nomeIndex),
                        cursor.getInt(idadeIndex),
                        cursor.getDouble(pesoIndex),
                        cursor.getString(racaIndex),
                        cursor.getString(corIndex),
                        cursor.getString(nomeDonoIndex),
                        cursor.getString(telefoneDonoIndex)
                );
                cursor.close();
                return animal;
            }
        }
        return null;
    }
}
