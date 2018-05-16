package Estudantes

fun main(args: Array<String>) {
    var alunoA = Estudante("Vinicius", "19-03-1999", 2017)
    var d1 = DisciplinaCursada("LP1", 1,2,10.0)
    var d2 = DisciplinaCursada("LP2", 2,1,10.0)
    var d3 = DisciplinaCursada("LP3", 2,2,9.0)

    alunoA.addDisciplinaCursada(d1)
    alunoA.addDisciplinaCursada(d2)
    //alunoA.delDisciplina(0)
    alunoA.alteraDisciplina("Linguagem de Programação 1", 1, 2, 5.7, 1)
    var alunoB = Estudante("Thiago", "19-03-1997", 2017)
    alunoB.addDisciplinaCursada(d2)
    alunoB.addDisciplinaCursada(d3)

    //alunoA.exibeEstudante()
    //alunoB.exibeEstudante()


    var turma : MutableList<Estudante> = mutableListOf(alunoA, alunoB)

    for(i in 0 until turma.size){
       turma[i].exibeEstudante()
    }

    // Deletar Estudantes
    //turma.removeAt(2)


}