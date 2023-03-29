import org.scalatest._
import org.scalatest.FlatSpec

import BisectionOpt._

class BisectionOptSpec extends FlatSpec{

    "findRootOpt(-10.0,10.0)(x => x+1.0)" should "equals to Some(-1.0)" in {
        assert(findRootOpt(-10.0,10.0)(x => x+1.0) == Some(-1.0))
    }

    "findRootOpt(-5.0,10.0)(x => 2.0-x)" should "equals to Some(2.0)" in {
        assert(findRootOpt(-5.0,10.0)(x => 2.0-x) == Some(2.0))
    }

    "findRootOpt(0.0,5.0)(x => x-1.0)" should "equals to Some(1.0)" in {
        assert(findRootOpt(0.0,5.0)(x => x-1.0) == Some(1.0))
    }

    "findRootOpt(0.0,2.0)(x => (x+1.0)*(x-1.0))" should "equals to Some(1.0)" in {
        assert(findRootOpt(0.0,2.0)(x => (x+1.0)*(x-1.0)) == Some(1.0))
    }

    "findRootOpt(-2.0,0.0)(x => (x+1.0)*(x-1.0))" should "equals to Some(-1.0)" in {
        assert(findRootOpt(-2.0,0.0)(x => (x+1.0)*(x-1.0)) == Some(-1.0))
    }

    "findRootOpt(0.0,2.0)(x => x*x-2.0)" should "equals to Some(math.sqrt(2.0))" in {
        assert(findRootOpt(0.0,2.0)(x => x*x-2.0) == Some(math.sqrt(2.0)))
    }

    "findRootOpt(2.0,0.0)(x => x-1.0)" should "equals to None" in {
        assert(findRootOpt(2.0,0.0)(x => x-1.0) == None)
    }

    "findRootOpt(-1.0,-3.0)(x => x+2.0)" should "equals to None" in {
        assert(findRootOpt(-1.0,-3.0)(x => x+2.0) == None)
    }

    "findRootOpt(0.0,2.0)(x => x+1.0)" should "equals to None" in {
        assert(findRootOpt(0.0,2.0)(x => x+1.0) == None)
    }

    "findRootOpt(0.0,2.0)(x => x-5.0)" should "equals to None" in {
        assert(findRootOpt(0.0,2.0)(x => x-5.0) == None)
    }

}


