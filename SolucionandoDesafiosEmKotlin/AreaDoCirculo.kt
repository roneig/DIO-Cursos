/*
 Área do Círculo

A fórmula para calcular a área de uma circunferência é: area = π * raio².
Considerando para este problema  π = 3.14159
Efetue o cálculo da área
elevando o valor de raio ao quadrado e multiplicando por π.

- Entrada
A entrada contém um valor de ponto flutuante (dupla precisão),
no caso, a variável raio.  ex: 2.00,  100.63, 150.00

- Saída
Apresentar a mensagem "A=" seguido pelo valor da variável area, conforme exemplo
abaixo, com 4 casas após o ponto decimal. Utilize variáveis de dupla precisão
(double). Como todos os problemas, não esqueça de imprimir o fim de linha após
o resultado, caso contrário, você receberá "Presentation Error".
ex: 12.5664, 31819.3103, 70685.7750

*/
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

fun main(args: Array<String>) {
    val raio = readLine()?.toDouble() ?: 0.0
    //val raio = 150.00 ?: 0.0
    val pi = 3.14159
    val area = raio.pow(2) * pi
    val resultado = BigDecimal(area).setScale(4, RoundingMode.HALF_EVEN)

    println("A=$resultado")
}