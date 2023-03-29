object Bisection{

  def findRoot(low : Double, high : Double)(f: Double => Double): Double = {
    
    val eps = 1.0e-8

    if((high - low) < eps){
      (low + high)/2
    } else { 
      val mid = (low + high)/2
      val fLow = f(low)
      val fMid = f(mid)
      val fHigh = f(high)

      if(fLow * fMid < 0.0){
        findRoot(low, mid)(f)
      } else if(fMid * fHigh < 0.0){
        findRoot(mid, high)(f)
      } else if(fMid == 0.0){
        mid
      } else {
        require(fLow * fHigh < 0.0)
        0.0
      }
    }
  }

}
