/*
lm-tests.scala

Test code for regression modelling

 */
import org.scalatest._
import org.scalatest.FlatSpec
import breeze.linalg._
import breeze.numerics._

import Regression._

class LmSpec extends FlatSpec {

  //import Utils._

  "Lm" should "handle 2 points on a horizontal line (manual intercept)" in {
    val y = DenseVector(5.0,5.0)
    val x = DenseMatrix((1.0,2.0),(1.0,4.0))
    val mod = Lm(y,x,List("Intercept","x"))
    val beta = DenseVector(5.0,0.0)
    assert(norm(mod.coefficients - beta) < 0.00001)
  }

  it should "handle 2 points on a slope (manual intercept)" in {
    val y = DenseVector(2.0,3.0)
    val x = DenseMatrix((1.0,2.0),(1.0,4.0))
    val mod = Lm(y,x,List("Intercept","x"))
    val beta = DenseVector(1.0,0.5)
    assert(norm(mod.coefficients - beta) < 0.00001)
    assert(abs(mod.rSquared - 1.0) < 0.00001)
  }

  it should "handle 3 points on a diagonal (manual intercept)" in {
    val y = DenseVector(4.0,5.0,6.0)
    val x = DenseMatrix((1.0,2.0),(1.0,3.0),(1.0,4.0))
    val mod = Lm(y,x,List("Intercept","x"))
    val beta = DenseVector(2.0,1.0)
    assert(norm(mod.coefficients - beta) < 0.00001)
    assert(abs(mod.rSquared - 1.0) < 0.00001)
  }

  it should "handle 2 points on a horizontal line (auto intercept)" in {
    val y = DenseVector(5.0,5.0)
    val x = DenseMatrix((2.0),(4.0))
    val mod = Lm(y,x,List("x"))
    val beta = DenseVector(5.0,0.0)
    assert(norm(mod.coefficients - beta) < 0.00001)
  }

  it should "handle 2 points on a slope (auto intercept)" in {
    val y = DenseVector(2.0,3.0)
    val x = DenseMatrix((2.0),(4.0))
    val mod = Lm(y,x,List("x"))
    val beta = DenseVector(1.0,0.5)
    assert(norm(mod.coefficients - beta) < 0.00001)
    assert(abs(mod.rSquared - 1.0) < 0.00001)
  }

  it should "handle 3 points on a diagonal (auto intercept)" in {
    val y = DenseVector(4.0,5.0,6.0)
    val x = DenseMatrix((2.0),(3.0),(4.0))
    val mod = Lm(y,x,List("x"))
    val beta = DenseVector(2.0,1.0)
    assert(norm(mod.coefficients - beta) < 0.00001)
    assert(abs(mod.rSquared - 1.0) < 0.00001)
  }

}

// eof


