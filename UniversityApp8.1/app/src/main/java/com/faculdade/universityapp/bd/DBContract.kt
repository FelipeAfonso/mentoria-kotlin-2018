package com.faculdade.universityapp.bd

import android.provider.BaseColumns

object DBContract {
    class Disciplinas_Ent : BaseColumns {
        companion object {
            val TABLE_NAME = "disciplinas"
            val COL_DISC_ID = "id"
            val COL_SIGLA = "sigla"
            val COL_NOME = "nome"
            val COL_SEM = "semestre"
            val COL_NOTA = "nota"
        }
    }
    class Estudantes_Ent : BaseColumns {
        companion object {
            val TABLE_NAME = "estudantes"
            val COL_STUD_ID = "idStudent"
            val COL_NOMESTUDENT = "nome"
            val COL_NASC = "nascimento"
            val COL_INGRESSO = "ingresso"
        }
    }
    class FirstDisc_Ent : BaseColumns {
        companion object {
            val TABLE_NAME = "firstDisc"
            val COL_FDISC_ID = "id"
            val COL_FSIGLA = "sigla"
            val COL_FNOME = "nome"
            val COL_FSEM = "semestre"
            val COL_FNOTA = "nota"
        }
    }
}