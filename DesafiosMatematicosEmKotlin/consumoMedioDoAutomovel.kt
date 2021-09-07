import java.util.Scanner
import java.util.Locale

fun main() {

    val leitor = Scanner(System.`in`)

    // reforça o uso de ponto decimal (ao invés da ',') na entrada de dados
    if (Locale.getDefault().toString() != "en_US") {
        leitor.useLocale(Locale.US)
    }

    //println(">Cálculo da média de consumo de combustível<")

    //println("1º - digite a distância (em Km) percorrida: ")
    val x: Int = leitor.nextInt()

    //println("2º - digite o valor (em litros) do combustível consumido: ")
    val y: Float = leitor.nextFloat()

    // Calcula e edita a média de consumo
    val media = x.div(y)
    //println("Consumo médio = ${String.format(Locale.US,"%.3f km/l", media)}")
    println(String.format(Locale.US,"%.3f km/l", media))
    leitor.close()
}