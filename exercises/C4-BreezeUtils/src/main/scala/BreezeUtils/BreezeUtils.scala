import breeze.stats.distributions._
import breeze.linalg._

object BreezeUtils{
    
    def arrayToDenseVector(a: Array[Double]): DenseVector[Double] = new DenseVector[Double](a)

    def denseVectorToArray(v: DenseVector[Double]): Array[Double] = v.toArray

    def arrayToDenseMatrix(aa: Array[Array[Double]]): DenseMatrix[Double] = DenseMatrix(aa:_*)

    def denseMatrixToArray(m: DenseMatrix[Double]): Array[Array[Double]] = (m(*,::).map(i => i.toArray)).toArray

    def exerciseMatrix(dm: DenseMatrix[Double]): DenseMatrix[Double] = {
      val mj = sum(dm(*,::)) /:/ dm.cols.toDouble
      val mi = sum(dm(::,*)) /:/ dm.rows.toDouble
      val mm = sum(dm)
      dm(*,::) :-= mi.t
      dm(::,*) :-= mj
      dm :+= mm
      dm
    }

    def main(args: Array[String]): Unit = {
      val uniformArray = Array.fill(100)(Uniform(0, 1).sample)
      val uniformDenseVector = arrayToDenseVector(uniformArray)
      val uniformArrayConverted = denseVectorToArray(uniformDenseVector)
      println(uniformArray)
      println(uniformDenseVector)
      println(uniformArrayConverted)
    }

}
