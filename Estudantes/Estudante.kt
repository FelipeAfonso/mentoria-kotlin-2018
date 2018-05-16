package Estudantes


class Estudante(var nome : String, var nascimento : String, var ingresso : Int,
                var disciplinas : MutableList<DisciplinaCursada> = mutableListOf(DisciplinaCursada())){
    var cursado : Int = 0
        set(value) {
            field = 2018-value
        }

    fun addDisciplinaCursada(disciplinaCursada: DisciplinaCursada){
        disciplinas.add(disciplinaCursada)
    }
    fun delDisciplina(indice : Int){
        disciplinas.removeAt(indice)
    }
    fun alteraDisciplina(nome : String, ano : Int, semestre : Int, nota : Double, index: Int){
        disciplinas[index].disciplina = nome
        disciplinas[index].ano = ano
        disciplinas[index].semestre = semestre
        disciplinas[index].nota = nota

    }
    fun exibeEstudante(){
        cursado = ingresso
        println("\n --- Aluno ---" +
                "\n Nome: $nome" +
                "\n Nascimento: $nascimento" +
                "\n Ingresso: $ingresso" +
                "\n Cursado: $cursado" +
                "\n --- Disciplinas Cursadas ---")
        for(i in 1 until disciplinas.size){
           println("-${disciplinas[i].disciplina}:" +
                   "\nAno: ${disciplinas[i].ano}" +
                   "\nSemestre: ${disciplinas[i].semestre}" +
                   "\nNota: ${disciplinas[i].nota}")
        }
    }



}