import scala.math._

object LogFactorial{

  def factorial(n : Int): Double = {
    if(n == 1) n 
    else n * factorial(n-1)
  }

  def logfact(n : Int): Double = log10(factorial(n))

  def logfact_2(n : Int): Double = {
    var i = 0
    var x : Double = 0

    for(i <- 1 to n){
      x = x + log10(i)
    }
    x
  }

  def main(args: Array[String]) : Unit = {
    val n = if (args.length == 1) args(0).toInt else 20
    printf("factorial(5): %f \n", factorial(5))
    printf("logfact(10): %f \n", logfact(10))
    printf("logfact_2(15): %f \n", logfact_2(15))
    printf(s"factorial($n): %f \n", factorial(n))
    printf(s"logfact($n): %f \n", logfact(n))
    printf(s"logfact_2($n): %f \n", logfact_2(n))
  }

}
