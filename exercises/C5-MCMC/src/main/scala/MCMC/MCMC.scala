
import breeze.linalg.{DenseMatrix, DenseVector}
import breeze.stats.distributions.{Gaussian, MultivariateGaussian}
import breeze.numerics.log
import breeze.stats.{mean, stddev}
import breeze.stats.distributions.Rand
import scala.collection.GenSeq

object MCMC{

  def metropolisHastings(x: GenSeq[Double], initial: Double, proposalScale: Double, iterations: Int): Array[Double] = {
    // Define log-likelihood function
    def ll(x: GenSeq[Double])(mean: Double,stdev: Double): Double = {
      val gau = Gaussian(mean,stdev)
      x.map(gau.logPdf).reduce(_+_)
    }

    // Define log-posterior function
    def logPosterior(x: GenSeq[Double])(mean: Double, stdev: Double): Double = {
      ll(x)(mean, stdev) + log(Gaussian(0.0, 100.0).pdf(mean)) + log(Gaussian(0.0, 100.0).pdf(stdev))
    }

    // Define proposal function
    def proposal(mean: Double, stdev: Double): (Double, Double) = {
      val proposalDist = MultivariateGaussian(DenseVector(mean, stdev), DenseMatrix.eye[Double](2) * proposalScale)
      val proposal = proposalDist.sample()
      (proposal(0), proposal(1))
    }

    // Initialize variables
    var current = initial
    val samples = Array.ofDim[Double](iterations)

    // Run Metropolis-Hastings algorithm
    for (i <- 0 until iterations) {
      val (meanProposal, stdevProposal) = proposal(current, 1.0)
      val logAcceptanceProb = logPosterior(x)(meanProposal, stdevProposal) - logPosterior(x)(current, 1.0)
      val acceptanceProb = math.min(1.0, math.exp(logAcceptanceProb))
      val accept = if (Rand.uniform.draw() < acceptanceProb) true else false

      if (accept) current = meanProposal

      samples(i) = current
    }

    samples
  }

  def main(args: Array[String]): Unit = {
    // Generate simulated data
    val trueMean = 2.0
    val trueStdDev = 1.5
    val largeData = Gaussian(trueMean, trueStdDev).sample(1000)

    // Run Metropolis-Hastings algorithm
    val samples = metropolisHastings(largeData, 0.0, 1.0, 10000)

    // Compute posterior mean and standard deviation
    val posteriorMean = mean(samples)
    val posteriorStdDev = stddev(samples)

    // Print results
    println(s"True mean: $trueMean, true standard deviation: $trueStdDev")
    println(s"Posterior mean: $posteriorMean, posterior standard deviation: $posteriorStdDev")
  }

}
