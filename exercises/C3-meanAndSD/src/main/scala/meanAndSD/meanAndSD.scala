import scala.math._
import scala.collection.GenSeq
import breeze.stats.distributions._
import scala.collection.parallel.immutable._

object meanSD{

  def meanAndSD(x : GenSeq[Double]): (Double,Double) = {
    val mean = x.sum/x.size
    val sd = sqrt((x.map(xi => pow(xi-mean,2)).sum)/(x.size-1)) 
    (mean,sd)
  }

  def main(args: Array[String]) : Unit = {
    val uniform_v = Vector.fill(10000)(Uniform(0, 1).sample)
    val uniform_pv = ParVector.fill(10000)(Uniform(0, 1).sample)
    printf(s"meanAndSD with Vector: %s \n", meanAndSD(uniform_v))
    printf(s"meanAndSD with ParVector: %s \n", meanAndSD(uniform_pv))
  }

}
