import scala.collection.GenSeq

object Monads{

  def weightedAverage(a : GenSeq[Double], w : GenSeq[Double]): Double  =  a.zip(w).map(t => t match { case(x,y) => x*y } ).sum/w.sum

  def concatenate(c : GenSeq[Char]): String = c.map(ci => ci.toString).reduce(_+_)

  def main(args: Array[String]) : Unit = {
    printf("weightedAverage(List(14.424, 14.421, 14.417, 14.413, 14.41),List(3058.0, 8826.0, 56705.0, 30657.0, 12984.0)): %f \n", weightedAverage(List(14.424, 14.421, 14.417, 14.413, 14.41),List(3058.0, 8826.0, 56705.0, 30657.0, 12984.0)))
    printf("weightedAverage(List(1.0, 2.0, 3.0, 4.0, 5.0),List(2.0, 8.0, 50.0, 30.0, 10.0)): %f \n", weightedAverage(List(1.0, 2.0, 3.0, 4.0, 5.0),List(2.0, 8.0, 50.0, 30.0, 10.0)))
    printf("concatenate(List('h','e','l','l','o')): %s \n", concatenate(List('h','e','l','l','o')))
    printf("concatenate(List('s','c','a','l','a','_','d','s')): %s \n", concatenate(List('s','c','a','l','a','_','d','s')))
  }

}
