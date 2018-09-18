package com.faculdade.universityapp.pages


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.faculdade.universityapp.R
import com.faculdade.universityapp.bd.Disciplina

class DisciplinasAdapter(private val context : Context, private val dados : ArrayList<Disciplina>) : BaseAdapter() {

    private val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dados.size
    }


    override fun getItemId(p0: Int): Long {
        return dados[p0].id.toLong()
    }

    override fun getItem(p0: Int): Any {
        return dados[p0]
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val rowView = inflater.inflate(R.layout.list_disciplinas_view, p2 , false)

        val siglaTextView = rowView.findViewById(R.id.disc_list_sigla) as TextView
        //val nomeTextView = rowView.findViewById(R.id.disc_list_nome) as TextView
        val semestreTextView = rowView.findViewById(R.id.disc_list_semestre) as TextView
        val notaTextView = rowView.findViewById(R.id.disc_list_nota) as TextView


        val disciplinas = getItem(p0) as Disciplina
        siglaTextView.text = disciplinas.sigla
        //nomeTextView.text = disciplinas.nome
        semestreTextView.text = disciplinas.semAno.toString()
        notaTextView.text = disciplinas.nota.toString()

        return rowView
    }


}




