package Estudante1

class DisciplinaCursada (
            var disciplina : String = "",
            var ano : Int = 0,
            var sigla : String = "",
            var nota : Double = 0.0,
            var status : String = ""
            ){

    fun exibeDisciplina(){
        println("\nDisciplina: ${disciplina}" +
                "\nAno: ${ano}" +
                "\nSigla: ${sigla}")
    }

    fun alteraStatus(){
        //nota >= 6 ? status = "Aprovado" : status = "Reprovado"        -----------> NAO POSSUI TERNARIO <-----------

        if(nota >= 6){
            status = "Aprovado"
        } else { status = "Reprovado" }
        /*
        when(nota){
            in 0.0 .. 6.0 -> status = "Reprovado"
            in 6 until 10 -> status = "Aprovado"
               else -> status = ""
        }
        */
    }



    fun alteraDisciplina(newdisc : String, newano: Int, newsigla : String){
        disciplina = newdisc
        ano = newano
        sigla = newsigla

        println("\n--- Atualização Sucedida ---" +
                "\nNovos resultados:" +
                "\nDisciplina: ${disciplina}" +
                "\nAno: ${ano}" +
                "\nSigla: ${sigla}")
    }

    fun corrigeNomeDisciplina(novo : String){
        disciplina = novo
    }



    }


