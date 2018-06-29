package Estudante1

class Estudante1 (var nome : String, var nascimento : String, var ingresso : Int, var disc : MutableList<DisciplinaCursada> = mutableListOf(DisciplinaCursada()), var cursado : Int = 2018-ingresso){

        fun exibeEstudante(){
            cursado = ingresso
            println("\n --- Aluno ---" +
                    "\n Nome: $nome" +
                    "\n Nascimento: $nascimento" +
                    "\n Ingresso: $ingresso" +
                    "\n Cursado: $cursado")
        }
        fun exibeBoletim(){
            val boletim = disc.groupBy {it.disciplina}
            boletim.forEach { if(it.key.isNotBlank() === true){
                println(it.key)
                it.value.forEach { println("Nota: "+it.nota+" Ano: "+it.ano+" Sigla: "+it.sigla+" Status: "+it.status) }

            }
            }
        }

        fun atualizaStatus(){
            disc.forEach {
                it.alteraStatus()
            }
        }

    fun mudaNotaDisc(id : Int, novanota : Double){
        //troque a nota de uma disciplina baseado em: sigla?
        disc[id].nota = novanota

    }

    fun trocaNomeDisc(id : Int, novonome : String){
        //corrija o nome de uma disciplina que foi escrito errado
        disc[id].disciplina = novonome
    }


    }
