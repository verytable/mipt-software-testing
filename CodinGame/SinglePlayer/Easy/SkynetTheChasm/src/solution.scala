import math._
import scala.util._
import scala.collection.mutable.HashMap

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
object Player {

    def main(args: Array[String]) {
        val r = readInt // the length of the road before the gap.
        val g = readInt // the length of the gap.
        val l = readInt // the length of the landing platform.
        
        /*
            contains possible speeds and next commands
            for all of the road cells
        */
        val ps = Array.fill(r + g + l)(new HashMap[Int, String]())
        
        /*
            fills ps for landing platform:
            0                for cell r + g + l - 1
            0, 1             for cell r + g + l - 2
            ...
        */
        ps(r + g + l - 1).put(1, "SLOW") // it is possible to "SLOW" to 0 in last cell
        for (pos <- r + g + l - 1 to r + g by -1) {
            ps(pos).put(0, "SLOW") // speed 0 on landing platform is always good
            for (speed <- ps(pos).keys) {
                val movedFrom = pos - speed
                if (movedFrom >= r + g) {
                    // "SPEED" to this cell
                    if (speed >= 1) {
                        ps(movedFrom).put(speed - 1, "SPEED")
                    }
                    // "SLOW" to this cell
                    ps(movedFrom).put(speed + 1, "SLOW")
                    // "WAIT" to this cell
                    ps(movedFrom).put(speed, "WAIT")
                }
            }
        }
        
        /*
            fills ps for the road cells where could be used "JUMP"
            according to the speeds on landing platform
        */
        for (pos <- r + g until r + g + l) {
            for (speed <- ps(pos).keys) {
                val jumpedFrom = pos - speed
                if (jumpedFrom < r) {
                    ps(jumpedFrom).put(speed, "JUMP")
                }
            }
        }
        
        /*
            fills ps for the road cells before the gap.
            from cell r + g - 1 it is possible only to "JUMP";
            
            ps is filled backwards: firstly fills those cells from
            where it is possible to get to the cells where "JUMP" occurs
            then fills the cells from where it is possible to get to the
            previous ones ...
        */
        for (pos <- r - 1 to 0 by -1) {
            for (speed <- ps(pos).keys) {
                val movedFrom = pos - speed
                if (movedFrom >= 0) {
                    if (speed >= 1) {
                        // "SPEED" to this cell
                        ps(movedFrom).put(speed - 1, "SPEED")
                        // "SLOW" to this cell
                        ps(movedFrom).put(speed + 1, "SLOW")
                        // "WAIT" to this cell
                        ps(movedFrom).put(speed, "WAIT")
                    }
                }
            }
        }
        
        /*
            prints possible speeds for all cells to cerr
        */
        for (pos <- 0 until r + g + l) {
            Console.err.print(pos)
            Console.err.print(": ")
            Console.err.println(ps(pos).toList.sorted.mkString(" "))
        }

        // game loop
        while(true) {
            val s = readInt // the motorbike's speed.
            val x = readInt // the position on the road of the motorbike.
            
            /*
                act as in ps
            */
            println(ps(x)(s))
        }
    }
}
