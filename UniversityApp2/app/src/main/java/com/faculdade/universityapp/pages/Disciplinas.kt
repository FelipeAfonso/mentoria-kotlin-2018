package com.faculdade.universityapp.pages

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteException
import android.provider.ContactsContract

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.faculdade.universityapp.R
import com.faculdade.universityapp.R.layout.activity_disciplinas
import com.faculdade.universityapp.bd.Disciplina
import com.faculdade.universityapp.bd.DisciplinaBDHelper
import kotlinx.android.synthetic.main.activity_disciplinas.*
import kotlinx.android.synthetic.main.disciplinas.*
import java.sql.SQLException
import java.util.ArrayList

class Disciplinas : Activity() {

    lateinit var DisciplinasDBHelper : DisciplinaBDHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.disciplinas)
        DisciplinasDBHelper = DisciplinaBDHelper(this)

        inserirDisciplinasIniciais()
        disciplinasCursadas()
        /*
        if (result){
            Toast.makeText(this, "Disciplinas inseridas com sucesso", Toast.LENGTH_SHORT).show()
            //val disciplinasCursadas = DisciplinasDBHelper.lerTodasDisciplinas()

            //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1 ,disciplinasCursadas)
            //listDisciplinas.adapter = adapter
        }else{
            Toast.makeText(this, "Erro ao inserir disciplinas", Toast.LENGTH_SHORT).show()
        }
        */
        }

    fun inserirDisciplinasIniciais(){
        var d1 = Disciplina(0,"LOG", "Lógica de Programação I", 1, 10.0)
        var d2 = Disciplina(0,"ING", "Inglês Técnico", 1, 9.5)
        var d3 = Disciplina(0,"CMED", "Comunicação e Expressão", 1, 10.0)
        var d4 = Disciplina(0,"IC", "Introdução a  Cálculo", 1, 7.0)
        var d5 = Disciplina(0,"ADM", "Introdução a Administração", 1, 8.0)

        try {
            DisciplinasDBHelper.inserirDisciplina(d1)
            DisciplinasDBHelper.inserirDisciplina(d2)
            DisciplinasDBHelper.inserirDisciplina(d3)
            DisciplinasDBHelper.inserirDisciplina(d4)
            DisciplinasDBHelper.inserirDisciplina(d5)
        }catch (e : SQLiteException){
            Toast.makeText(this,"Não foi possível inserir as disciplinas iniciais",Toast.LENGTH_SHORT).show()
        }
    }

    fun disciplinasCursadas() {
        val disciplinas = DisciplinasDBHelper.lerTodasDisciplinas()

        disciplinas.forEach {
            Toast.makeText(this, "ID: ${it.id} SIGLA: ${it.sigla} NOME: ${it.nome} SEMESTRE: ${it.semAno} NOTA: ${it.nota}", Toast.LENGTH_LONG).show()
        }
    }

}

