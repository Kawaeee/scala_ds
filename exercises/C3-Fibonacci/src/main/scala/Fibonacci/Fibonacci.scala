object Fibonacci{

  def fib1(f0 : Int, f1 : Int, n : Int): Int = {
    var i = 1
    var a = f0
    var b = f1
    var sum = 0
    while(i<n){
      sum = a+b
      a = b
      b = sum
      i += 1
    }
    sum
  }

  def fib2(f0 : Int, f1 : Int, n : Int): Int = {
    if(n==0) f0
    else if(n==1) f1
    else fib2(f0,f1,n-1) + fib2(f0,f1,n-2)
  }
  
  @annotation.tailrec
  def fib3(f0 : Int, f1 : Int, n : Int): Int = {
    if(n==0) f0
    else if(n==1) f1
    else fib3(f1,f0+f1,n-1)
  }
  
  def fib4(f0 : Int, f1 : Int, n : Int): Int = {
    def fib(f0 : Int,f1 : Int): Stream[Int] = f0 #:: fib(f1,f0+f1)
    fib(f0,f1).take(n+1).toList.last
  }

  def main(args: Array[String]) : Unit = {
    val f0 = if (args.length == 3) args(0).toInt else 5
    val f1 = if (args.length == 3) args(1).toInt else 8
    val n = if (args.length == 3) args(2).toInt else 10
    printf(s"fib1(1,2,3): %d \n", fib1(1,2,3))
    printf(s"fib1($f0,$f1,$n): %d \n", fib1(f0,f1,n))
    printf(s"fib2($f0,$f1,$n): %d \n", fib2(f0,f1,n))
    printf(s"fib3($f0,$f1,$n): %d \n", fib3(f0,f1,n))
    printf(s"fib4($f0,$f1,$n): %d \n", fib4(f0,f1,n))
  }

}
