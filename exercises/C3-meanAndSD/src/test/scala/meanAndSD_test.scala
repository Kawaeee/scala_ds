import org.scalatest._
import org.scalatest.FlatSpec

import meanSD._

class meanAndSDSpec extends FlatSpec{

    "meanAndSD(List(0.0,1.0,2.0,3.0,4.0,5.0))" should "equals to (2.5,1.8708286933869707)" in {
        assert(meanAndSD(List(0.0,1.0,2.0,3.0,4.0,5.0)) == (2.5,1.8708286933869707))
    }

}


