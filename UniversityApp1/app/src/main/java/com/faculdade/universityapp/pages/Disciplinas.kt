package com.faculdade.universityapp.pages

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.provider.ContactsContract

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.faculdade.universityapp.R
import com.faculdade.universityapp.R.layout.activity_disciplinas
import com.faculdade.universityapp.bd.Disciplina
import kotlinx.android.synthetic.main.activity_disciplinas.*
import kotlinx.android.synthetic.main.disciplinas.*

class Disciplinas : Activity() {

    lateinit var DisciplinasDBHelper : DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.disciplinas)

        DisciplinasDBHelper = DatabaseHandler(this)
        var d1 = Disciplina()
        d1.nome = "Linguagem de Programação I"
        d1.sigla = "LP1"
        d1.semAno = 2
        d1.nota = 8.0

        var result = DisciplinasDBHelper.inserirDisciplinaTeste(d1)

        if (result){
            Toast.makeText(this, "Disciplinas inseridas com sucesso", Toast.LENGTH_SHORT).show()
            //val disciplinasCursadas = DisciplinasDBHelper.lerTodasDisciplinas()

            //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1 ,disciplinasCursadas)
            //listDisciplinas.adapter = adapter
        }else{
            Toast.makeText(this, "Erro ao inserir disciplinas", Toast.LENGTH_SHORT).show()
        }

        }
    }

