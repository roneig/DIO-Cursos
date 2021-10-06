/*
   Coluna na Matriz

Neste problema você deve ler um número que indica uma coluna de uma matriz na
qual uma operação deve ser realizada, um caractere maiúsculo, indicando a
operação que será realizada, e todos os elementos de uma matriz M[12][12]. Em
seguida, calcule e mostre a soma ou a média dos elementos , conforme for o caso.

- Entrada

A primeira linha de entrada contém um número C (0 <= C <= 11) indicando a coluna
que será considerada para operação. A segunda linha de entrada contém um único
caractere Maiúsculo T ('S' ou 'M'), indicando a operação (Soma ou Média) que
deverá ser realizada com os elementos da matriz. Seguem os 144 valores de ponto
flutuante que compõem a matriz.

obs.: para fins didáticos, o pgm simula a leitura dos parâmetros
(o objetivo do exercício é o trabalho com matrizes e não acesso a dados)

- Saída

Imprima o resultado solicitado (a soma ou média),
com 1 casa após o ponto decimal.

*/

fun main(args: Array<String>) {
    val C = 5      // Coluna informada
    var sum = 0.0
    val T = "S"    // Operação selecionada
    val M = Array(12) { DoubleArray(12) }


    for (i in 0 until M.size) {
        for (j in 0 until M.size) {
            // gera matriz
            M[i][j] = (i * j).toDouble()
        }
    }

    // imprime a matriz gerada
    for (i in 0 until M.size){
        var linha: String = ""
        for (j in 0 until M.size){
            linha = linha + (M[i][j]).toString().padStart(5, '0') + " "
        }
        println(linha)
    }


    for (k in 0.until(M.size)) {
        sum += M[k][C]
    }
    if (T == "M") sum /= M.size

    println(sum.format(1))
}

private fun Double.format(digits: Int) = "%.${digits}f".format(this).replace(',', '.')
