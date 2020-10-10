import java.io.{BufferedReader, BufferedWriter}
import java.nio.file.Paths
import java.nio.file.Files.{newBufferedReader, newBufferedWriter}

//3.5
def fizzBuzz(): Unit = {
  for (curr <- Range.inclusive(1, 100)) {
    if (curr % 3 == 0 && curr % 5 == 0) println("FizzBuzz")
    else if (curr % 5 == 0) println("Buzz")
    else if (curr % 3 == 0) println("Fizz")
    else println(curr)
  }
}

//fizzBuzz()
def flexibleFizzBuzz(f: String => Unit): Unit = {
  val fizzBuzz = for (curr <- Range.inclusive(1, 100)) yield {
    if (curr % 3 == 0 && curr % 5 == 0) "FizzBuzz"
    else if (curr % 5 == 0) "Buzz"
    else if (curr % 3 == 0) "Fizz"
    else curr.toString
  }

  fizzBuzz.foreach(f)
}

var i = 0
val output = new Array[String](100)

flexibleFizzBuzz{s =>
  output(i) = s
  i += 1
}

output//.foreach(println)

//3.6
class Msg(val id: Int, val parent: Option[Int], val txt: String)

val messages = Array(
  new Msg(0, None, "Hello"),
  new Msg(1, Some(0), "World"),
  new Msg(2, None, "I am Cow"),
  new Msg(3, Some(2), "Hear me moo"),
  new Msg(4, Some(2), "Here I stand"), new Msg(5, Some(2), "I am Cow"),
  new Msg(6, Some(5), "Here me moo, moo"))

def printMessages(messages: Array[Msg]): Unit = {
  def printFrag(parent: Option[Int], indent: String): Unit = {
    for (msg <- messages if msg.parent == parent) {
      println(s"$indent#${msg.id} ${msg.txt}")
      printFrag(Some(msg.id), indent + "    ")
    }
  }
  printFrag(None, "")
}

printMessages(messages)

//3.7
def withFileWriter[T](fileName: String)(handler: BufferedWriter => T) = {
  val output = newBufferedWriter(Paths.get(fileName))
  try handler(output)
  finally output.close()
}

def withFileReader[T](fileName: String)(handler: BufferedReader => T) = {
  val input = newBufferedReader(Paths.get(fileName))
  try handler(input)
  finally input.close()
}