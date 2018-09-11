package com.faculdade.universityapp.bd

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper



class EstudanteBDHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun inserirEstudante(student : Estudante): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(DBContract.Estudantes_Ent.COL_NOMESTUDENT, student.nome)
        values.put(DBContract.Estudantes_Ent.COL_NASC, student.nascimento)
        values.put(DBContract.Estudantes_Ent.COL_INGRESSO, student.ingresso)
        val newRowId = db.insert(DBContract.Estudantes_Ent.TABLE_NAME, null, values)

        return true
    }


    @Throws(SQLiteConstraintException::class)
    fun editarEstudante(student: Estudante) : Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put(DBContract.Estudantes_Ent.COL_NOMESTUDENT, student.nome)
        values.put(DBContract.Estudantes_Ent.COL_NASC, student.nascimento)
        values.put(DBContract.Estudantes_Ent.COL_INGRESSO, student.ingresso)
        db.update(DBContract.Estudantes_Ent.TABLE_NAME, values, DBContract.Estudantes_Ent.COL_STUD_ID + "=" + student.id, null)
        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun apagarEstudante(stud_id: String): Boolean {
        val db = writableDatabase
        val selection = DBContract.Estudantes_Ent.COL_STUD_ID + " LIKE ?"
        val selectionArgs = arrayOf(stud_id)
        db.delete(DBContract.Disciplinas_Ent.TABLE_NAME, selection, selectionArgs)

        return true
    }

    fun lerEstudante(Id : Int): ArrayList<Estudante> {
        val estudante = ArrayList<Estudante>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.Estudantes_Ent.TABLE_NAME + " WHERE " + DBContract.Estudantes_Ent.COL_STUD_ID + "='" + Id + "'", null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var stud_id: Int
        var stud_nome: String
        var nasc: String
        var ingresso: Int
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                stud_id = cursor.getInt(cursor.getColumnIndex(DBContract.Estudantes_Ent.COL_STUD_ID))
                stud_nome = cursor.getString(cursor.getColumnIndex(DBContract.Estudantes_Ent.COL_NOMESTUDENT))
                nasc = cursor.getString(cursor.getColumnIndex(DBContract.Estudantes_Ent.COL_NASC))
                ingresso = cursor.getInt(cursor.getColumnIndex(DBContract.Estudantes_Ent.COL_INGRESSO))

                estudante.add(Estudante(stud_id,stud_nome, nasc, ingresso))
                cursor.moveToNext()
            }
        }
        return estudante
    }

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "EstudantesUniversity"

        private val SQL_CREATE_ENTRIES =
                "CREATE TABLE " + DBContract.Estudantes_Ent.TABLE_NAME + " (" +
                        DBContract.Estudantes_Ent.COL_STUD_ID + " INTEGER PRIMARY KEY," +
                        DBContract.Estudantes_Ent.COL_NOMESTUDENT + " TEXT," +
                        DBContract.Estudantes_Ent.COL_NASC + " TEXT," +
                        DBContract.Estudantes_Ent.COL_INGRESSO + " INTEGER);"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.Estudantes_Ent.TABLE_NAME
    }
}
