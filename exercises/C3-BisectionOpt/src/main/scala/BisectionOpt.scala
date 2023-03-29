object BisectionOpt{

  def findRootOpt(low : Double, high : Double)(f: Double => Double): Option[Double] = {
    // Optional findRoot
    val eps = 1.0e-8
    
    if((high - low) < eps){
      Some((low + high)/2)
    } else {
      val mid = (low + high)/2
      val fLow = f(low)
      val fMid = f(mid)
      val fHigh = f(high)

      if(fLow * fMid < 0.0){
        findRootOpt(low, mid)(f)
      } else if(fMid * fHigh < 0.0){
        findRootOpt(mid, high)(f)
      } else if(fMid == 0.0){
        Some(mid)
      } else {
        // require(fLow * fHigh < 0.0)
        None
      }
    }
  }

}
