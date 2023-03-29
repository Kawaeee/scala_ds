import breeze.stats.distributions._
import breeze.plot._

class BinomialWithPoissonAndBeta(val poissonMean: Double, val betaA: Double, val betaB: Double) extends Rand[Int] {

  def draw(): Int = {
    val p = new Poisson(poissonMean)
    val b = new Beta(betaA,betaB)
    (new Binomial(p.sample(),b.sample).sample)
  }

  def iterator: Iterator[Int] = Iterator.continually(draw())

  def stream(): Stream[Int] = iterator.toStream

  def stream(n : Int): Stream[Int] = Stream.fill(n)(draw())

}

object rmixure{
  
  def apply(poissonMean: Double, betaA : Double, betaB : Double): Double = {
    val b = new BinomialWithPoissonAndBeta(poissonMean,betaA,betaB)
    val r = b.stream().take(10000).toList
    val avg = r.sum/r.size
    plot(r)
    (avg)
  }
  
  def plot(n : List[Int]){
    val f = Figure()
    val p = f.subplot(0)
    p += hist(n,50)
    f.refresh
  }

  def main(args: Array[String]) : Unit = {
    val poissonMean = if (args.length == 3) args(0).toDouble else 20.0
    val betaA = if (args.length == 3) args(1).toDouble else 4.0
    val betaB = if (args.length == 3) args(2).toDouble else 4.0
    printf(s"apply($poissonMean,$betaA,$betaB): %f \n", apply(poissonMean,betaA,betaB))
  }

}
