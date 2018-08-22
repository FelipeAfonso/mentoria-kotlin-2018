package com.faculdade.universityapp.pages


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.faculdade.universityapp.bd.Disciplina


class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
                ID + " INTEGER PRIMARY KEY," +
                SIGLA + " TEXT," + NOME + " TEXT," +
                SEM + " INTEGER," +
                NOTA + "TEXT);"
            db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun inserirDisciplinaTeste(disc: Disciplina) : Boolean{
        var db = this.writableDatabase
        db.execSQL("INSERT INTO ${TABLE_NAME} ("+
                SIGLA +"," +
                NOME +"," +
                SEM +"," +
                NOTA +"," +
                ") VALUES ("+
                disc.sigla + "," +
                disc.nome +"," +
                disc.semAno +"," +
                disc.nota +")")

        db.close()
        return true
    }
    fun addDisciplina(disc: Disciplina): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(SIGLA, disc.sigla)
        values.put(NOME, disc.nome)
        values.put(SEM, disc.semAno)
        values.put(NOTA, disc.nota)
        val _successo = db.insert(TABLE_NAME, null, values)
        db.close()
        return (Integer.parseInt("$_successo") != -1)
    }

    companion object {

        private val DB_VERSION = 1
        private val DB_NAME = "DisciplinasUniversity"
        private val TABLE_NAME = "Disciplinas"
        private val ID = "Id"
        private val SIGLA = "Sigla"
        private val NOME = "Nome"
        private val SEM = "Semestre/Ano"
        private val NOTA = "Nota"
    }
}


/*
class DisciplinaBDHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun inserirDisciplina(disc : Disciplina): Boolean {
        // Gets the data repository in write mode
        val db = this.writableDatabase
        // Create a new map of values, where column names are the keys
        val values = ContentValues()
        values.put(DBContract.Disciplinas_Ent.COL_DISC_ID, 1)
        values.put(DBContract.Disciplinas_Ent.COL_SIGLA, disc.sigla)
        values.put(DBContract.Disciplinas_Ent.COL_NOME, disc.nome)
        values.put(DBContract.Disciplinas_Ent.COL_SEM, disc.semAno)
        values.put(DBContract.Disciplinas_Ent.COL_NOTA, disc.nota)

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(DBContract.Disciplinas_Ent.TABLE_NAME, null, values)

        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun inserirPrimeirasDisciplinas():Boolean{
        val db = writableDatabase
        var d1 = Disciplina("LP1", "Linguagem de Programação", 20182, 10.0)
        var d2 = Disciplina("LOG", "Lógica de Programação", 20172, 4.0)
        var d3 = Disciplina("LOG", "Lógica de Programação", 20181, 8.0)
        val values = ContentValues()

        // Disciplina 1
        values.put(DBContract.Disciplinas_Ent.COL_SIGLA, d1.sigla)
        values.put(DBContract.Disciplinas_Ent.COL_NOME, d1.nome)
        values.put(DBContract.Disciplinas_Ent.COL_SEM, d1.semAno)
        values.put(DBContract.Disciplinas_Ent.COL_NOTA, d1.nota)
        db.insert(DBContract.Disciplinas_Ent.TABLE_NAME, null, values)
        return true
        /*
        //Disciplina 2
        values.put(DBContract.Disciplinas_Ent.COL_SIGLA, d2.sigla)
        values.put(DBContract.Disciplinas_Ent.COL_NOME, d2.nome)
        values.put(DBContract.Disciplinas_Ent.COL_SEM, d2.semAno)
        values.put(DBContract.Disciplinas_Ent.COL_NOTA, d2.nota)
        db.insert(DBContract.Disciplinas_Ent.TABLE_NAME, null, values)

        //Disciplina 3
        values.put(DBContract.Disciplinas_Ent.COL_SIGLA, d3.sigla)
        values.put(DBContract.Disciplinas_Ent.COL_NOME, d3.nome)
        values.put(DBContract.Disciplinas_Ent.COL_SEM, d3.semAno)
        values.put(DBContract.Disciplinas_Ent.COL_NOTA, d3.nota)
        db.insert(DBContract.Disciplinas_Ent.TABLE_NAME, null, values)
        return true
        */
    }

    @Throws(SQLiteConstraintException::class)
    fun apagarDisciplina(disc_id: String): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = DBContract.Disciplinas_Ent.COL_DISC_ID + " LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(disc_id)
        // Issue SQL statement.
        db.delete(DBContract.Disciplinas_Ent.TABLE_NAME, selection, selectionArgs)

        return true
    }

    fun lerDisciplina(Id : Int): ArrayList<Disciplina> {
        val disciplinas = ArrayList<Disciplina>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.Disciplinas_Ent.TABLE_NAME + " WHERE " + DBContract.Disciplinas_Ent.COL_DISC_ID + "='" + Id + "'", null)
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var disc_id: Int
        var sigla: String
        var nome: String
        var semAno: Int
        var nota: Double
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                disc_id = cursor.getInt(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_DISC_ID))
                sigla = cursor.getString(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_SIGLA))
                nome = cursor.getString(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_NOME))
                semAno = cursor.getInt(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_SEM))
                nota = cursor.getDouble(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_NOTA))

                disciplinas.add(disc_id,Disciplina(sigla, nome, semAno, nota))
                cursor.moveToNext()
            }
        }
        return disciplinas
    }

    fun lerTodasDisciplinas(): ArrayList<Disciplina> {
        val disciplinas = ArrayList<Disciplina>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.Disciplinas_Ent.TABLE_NAME + "ORDER BY " + DBContract.Disciplinas_Ent.COL_SEM, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var disc_id: Int
        var sigla: String
        var nome: String
        var semAno: Int
        var nota: Double
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                disc_id = cursor.getInt(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_DISC_ID))
                sigla = cursor.getString(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_SIGLA))
                nome = cursor.getString(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_NOME))
                semAno = cursor.getInt(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_SEM))
                nota = cursor.getDouble(cursor.getColumnIndex(DBContract.Disciplinas_Ent.COL_NOTA))

                disciplinas.add(disc_id,Disciplina(sigla, nome, semAno, nota))
                cursor.moveToNext()
            }
        }
        return disciplinas
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "DisciplinasUniversity"

        private val SQL_CREATE_ENTRIES =
                "CREATE TABLE " + DBContract.Disciplinas_Ent.TABLE_NAME + " (" +
                        DBContract.Disciplinas_Ent.COL_DISC_ID + " INTEGER PRIMARY KEY," +
                        DBContract.Disciplinas_Ent.COL_SIGLA + " TEXT," +
                        DBContract.Disciplinas_Ent.COL_NOME + " TEXT," +
                        DBContract.Disciplinas_Ent.COL_SEM + " INTEGER," +
                        DBContract.Disciplinas_Ent.COL_NOTA + " TEXT);"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.Disciplinas_Ent.TABLE_NAME
    }
}
        */
