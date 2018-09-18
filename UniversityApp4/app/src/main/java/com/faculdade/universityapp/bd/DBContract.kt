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
}