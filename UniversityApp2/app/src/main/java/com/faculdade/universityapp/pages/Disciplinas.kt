package com.faculdade.universityapp.pages

import android.os.Bundle
import android.app.Activity
import android.widget.ArrayAdapter

import android.widget.Toast
import com.faculdade.universityapp.R
import com.faculdade.universityapp.R.layout.activity_disciplinas
import com.faculdade.universityapp.bd.Disciplina
import com.faculdade.universityapp.bd.DisciplinaBDHelper
import kotlinx.android.synthetic.main.disciplinas.*

class Disciplinas : Activity() {

    lateinit var DisciplinasDBHelper : DisciplinaBDHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.disciplinas)
        DisciplinasDBHelper = DisciplinaBDHelper(this)

        //disciplinasIniciais()
        pesquisaDisciplina(2)
        //todasDisc()
        }

    //Inserir disciplinas iniciais
    fun disciplinasIniciais(){

        //var d1 = Disciplina(0,"LP1","Linguagem de Programação I", 2,8.0)

        var result = DisciplinasDBHelper.inserirPrimeirasDisciplinas()

        if (result){
            Toast.makeText(this, "Disciplinas iniciais inseridas com sucesso", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Erro ao inserir disciplinas", Toast.LENGTH_SHORT).show()
        }
    }

    fun pesquisaDisciplina(id : Int){
        var discFound = DisciplinasDBHelper.lerDisciplina(id)

        discFound.forEach{
            Toast.makeText(this, "Sigla: ${it.sigla} || Nome: ${it.nome} || Semestre: ${it.semAno} || Nota: ${it.nota}", Toast.LENGTH_LONG).show()
        }
    }

    fun todasDisc(){
        var disciplinas = DisciplinasDBHelper.lerTodasDisciplinas()
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, disciplinas)

        disciplinas.forEach {
            listView.adapter = adapter
            Toast.makeText(this, "ID: ${it.id} || Sigla: ${it.sigla} || Nome: ${it.nome} || Semestre: ${it.semAno} || Nota: ${it.nota}", Toast.LENGTH_LONG).show()
        }

    }
    }



