import breeze.linalg._
import breeze.numerics._
import breeze.stats.distributions.{Gaussian,Poisson,Binomial}

object GLM {

@annotation.tailrec
def IRLS(
  b: Double => Double,
  bp: Double => Double,
  bpp: Double => Double,
  y: DenseVector[Double],
  X: DenseMatrix[Double],
  bhat0: DenseVector[Double],
  its: Int,
  tol: Double = 0.0000001
): DenseVector[Double] = if (its == 0) {
  println("WARNING: IRLS did not converge")
  bhat0
} else {
  val eta = X * bhat0
  val W = diag(eta map bpp)
  val z = y - (eta map bp)
  val bhat = bhat0 + (X.t * W * X) \ (X.t * z)
  if (norm(bhat-bhat0) < tol) bhat else
    IRLS(b,bp,bpp,y,X,bhat,its-1,tol)
}


def IRLS_N(
  b: Double => Double,
  bp: Double => Double,
  bpp: Double => Double,
  y: DenseVector[Double],
  X: DenseMatrix[Double],
  bhat0: DenseVector[Double],
  its: Int,
  tol: Double = 0.0000001
): DenseVector[Double] = if (its == 0) {
  println("WARNING: IRLS did not converge")
  bhat0
} else {
  val eta = X * bhat0
  val W = eta map bpp
  val z = y - (eta map bp)
  val bhat = bhat0 + (X.t(*,::) * W * X) \ (X.t * z)
  if (norm(bhat-bhat0) < tol) bhat else
    IRLS(b,bp,bpp,y,X,bhat,its-1,tol)
}

def IRLS_NEW(
  b: Double => Double,
  bp: Double => Double,
  bpp: Double => Double,
  y: DenseVector[Double],
  X: DenseMatrix[Double],
  bhat0: DenseVector[Double],
  its: Int,
  tol: Double = 0.0000001
): DenseVector[Double] = if (its == 0) {
  println("WARNING: IRLS did not converge")
  bhat0
} else {
  val m_to_ar = denseMatrixToArray(X)
  val gg = m_to_ar(0).filter(_!=1.0).toList
  val d = gg.map(i => DenseVector((i :: List(1.0)).toArray.reverse))
  val dv = DenseVector(d.toArray)
  val tmp = dv.map(i => i *:* bhat0 )
  val eta =  tmp.map(x => x(0)+x(1))
  val W = diag(eta map bpp)
  val z = y - (eta map bp)
  val bhat = bhat0 + (X.t * W * X) \ (X.t * z)
  if (norm(bhat-bhat0) < tol) bhat else
   IRLS(b,bp,bpp,y,X,bhat,its-1,tol)
}

def logReg(
  y: DenseVector[Double],
  X: DenseMatrix[Double],
  its: Int = 30
): DenseVector[Double] = {
  def expit(x: Double): Double = 1.0/(1.0+math.exp(-x))
  def b(x: Double): Double = math.log(1.0+math.exp(x))
  def bp(x: Double): Double = expit(x)
  def bpp(x: Double): Double = {
    val e = math.exp(-x)
    e/((1.0+e)*(1.0+e))
  }
  val bhat0 = DenseVector.zeros[Double](X.cols)
  IRLS(b,bp,bpp,y,X,bhat0,its)
}

def poiReg(
  y: DenseVector[Double],
  X: DenseMatrix[Double],
  its: Int = 30
): DenseVector[Double] = {
  val bhat0 = DenseVector.zeros[Double](X.cols)
  IRLS(math.exp,math.exp,math.exp,y,X,bhat0,its)
}

def expit(x: Double): Double = 1.0/(1.0+math.exp(-x))

def fd: Double => Double = expit(_)

def denseMatrixToArray(m: DenseMatrix[Double]): Array[Array[Double]] = Array(m.t.toArray)

def arrayToDenseVector(a: Array[Double]): DenseVector[Double] = DenseVector(a)

def time[A](f: => A) = {
  val s = System.nanoTime
  val ret = f
  println("time: "+(System.nanoTime-s)/1e6+"ms")
  ret
}

def main(args : Array[String]){
  val N = 2000
  val beta = DenseVector(0.1,0.3)
  val ones = DenseVector.ones[Double](N)
  val x = DenseVector(Gaussian(1.0,3.0).sample(N).toArray)
  val X = DenseMatrix.vertcat(ones.toDenseMatrix, x.toDenseMatrix).t
  val theta = X * beta
  val pr = theta map expit
  val y = pr map (pi => (new Binomial(1,pi)).draw) map (_.toDouble)
  val betahat = logReg(y,X,its=100)
  val simple = IRLS(fd,fd,fd,y,X,betahat,1)
  val efficient = IRLS_NEW(fd,fd,fd,y,X,betahat,1)
  val other_way = IRLS_N(fd,fd,fd,y,X,betahat,1)
  println(simple)
  time(simple)
  println(efficient)
  time(efficient)
  println(other_way)
  time(other_way)
}

}
