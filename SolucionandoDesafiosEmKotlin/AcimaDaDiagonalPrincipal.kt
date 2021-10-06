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
    var sum = 0.0
    val T =  readLine()!!.uppercase()
    val M = Array(12) { DoubleArray(12) }
    var read: String

    for (i in 0 until M.size) {
        for (j in 0 until M.size) {
            read = readLine()!!
            try{
                M[i][j] = read.toDouble()
            } catch (e: Exception) {
                M[i] = read.split(" ").map(String::toDouble).toDoubleArray()
                break
            }
        }
    }

    for (i in 0 until M.size - 1) {
        for (j in (i + 1) until M.size) {
            sum += M[i][j]
        }
    }

    if (T == "M") sum /=  ((M.size*M.size)-M.size) / 2

    println(sum.format(1))
}

private fun Double.format(digits: Int) = "%.${digits}f".format(this).replace(',', '.')
