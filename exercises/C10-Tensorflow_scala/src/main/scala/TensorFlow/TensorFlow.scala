import org.platanios.tensorflow.api._

object TensorFlowApp {

  def main(args: Array[String]): Unit = {
    
    val session = Session()

    // Tensor addition
    val addA = tf.constant(Tensor[Int](Seq(1, 2, 3)))
    val addB = tf.constant(Tensor[Int](Seq(4, 5, 6)))
    val addC = tf.add(addA, addB)
    val addResult = session.run(fetches = addC)
    printf("Tensor Addition: %s \n", addResult.entriesIterator.toList)

    // Tensor subtraction
    val subA = tf.constant(Tensor[Int](Seq(1, 2, 3)))
    val subB = tf.constant(Tensor[Int](Seq(4, 5, 6)))
    val subC = tf.subtract(subA, subB)
    val subResult = session.run(fetches = subC)
    printf("Tensor Subtraction: %s \n", subResult.entriesIterator.toList)
    
    // Tensor multiplication
    val mulA = tf.constant(Tensor[Int](Seq(1, 2, 3)))
    val mulB = tf.constant(Tensor[Int](Seq(4, 5, 6)))
    val mulC = tf.multiply(mulA, mulB)
    val mulResult = session.run(fetches = mulC)
    printf("Tensor Multiplication: %s \n", mulResult.entriesIterator.toList)

    // Tensor division
    val divA = tf.constant(Tensor[Int](Seq(1, 2, 3)))
    val divB = tf.constant(Tensor[Int](Seq(4, 5, 6)))
    val divC = tf.divide(divA, divB)
    val divResult = session.run(fetches = divC)
    printf("Tensor Division: %s \n", divResult.entriesIterator.toList)

    // Tensor square
    val squareA = tf.constant(Tensor[Int](Seq(1, 2, 3, 4)))
    val squareB = tf.square(squareA)
    val squareResult = session.run(fetches = squareB)
    printf("Tensor Square: %s \n", squareResult.entriesIterator.toList)

    session.close()
  }

}
