import breeze.stats.distributions.Gaussian
import scala.collection.GenSeq

object MCMC{

  def ll(x: GenSeq[Double])(mean: Double, stdev: Double): Double = {
    val gau = Gaussian(mean,stdev)
    x.map(gau.logPdf).reduce(_+_)
  }

}
