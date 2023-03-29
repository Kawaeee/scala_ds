import org.scalatest._
import org.scalatest.FlatSpec

import Bisection._

class BisectionSpec extends FlatSpec{

    "findRoot(-10.0,10.0)(x => x+1.0)" should "equals to -1.0" in {
        assert(findRoot(-10.0,10.0)(x => x+1.0) == -1.0)
    }

    "findRoot(-5.0,10.0)(x => 2.0-x)" should "equals to 2.0" in {
        assert(findRoot(-5.0,10.0)(x => 2.0-x) == 2.0)
    }

    "findRoot(0.0,5.0)(x => x-1.0)" should "equals to 1.0" in {
        assert(findRoot(0.0,5.0)(x => x-1.0) == 1.0)
    }

    "findRoot(0.0,2.0)(x => (x+1.0)*(x-1.0))" should "equals to 1.0" in {
        assert(findRoot(0.0,2.0)(x => (x+1.0)*(x-1.0)) == 1.0)
    }

    "findRoot(-2.0,0.0)(x => (x+1.0)*(x-1.0))" should "equals to -1.0" in {
        assert(findRoot(-2.0,0.0)(x => (x+1.0)*(x-1.0)) == -1.0)
    }

    "findRoot(0.0,2.0)(x => x*x-2.0)" should "equals to math.sqrt(2.0)" in {
        assert(findRoot(0.0,2.0)(x => x*x-2.0) == math.sqrt(2.0))
    }
}


