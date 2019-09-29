

1. In `Driver.java` implement the following generic method:

   ```java
   /**
    * Given a reference to an array specified by {@code array} and a reference
    * specified by {@code val}, finds the first object {@code o} in {@code array} 
    * such that {@code Math.abs(val.area() - o.area()) < delta}, then returns its
    * reference; returns {@code null} if no match is found. 
    *
    * @param <T>    element type
    * @param array  array to search
    * @param val    reference to the object to match
    * @param delta  difference that is considered unequal
    * @return a reference to the object from {@code array} with an area matching
    * that of the object referenced by {@code val} or {@code null} if no match
    * is found.
    */
   public static <T extends Shape> T findMatchingArea(T[] array, T val, double delta) {
       ...
   } // findMatchingArea
   ```
   
   1. **Snippet Example:**
   
   1. **Snippet 1:**
   
      ```java
      Rectangle a = findMatchingArea(rectangles, new Square(2.0));
      Square b    = findMatchingArea(rectangles, new Square(2.0));
      Shape c     = findMatchingArea(rectangles, new Square(2.0));
      ```
   
   1. **Snippet 1:**
   
   1. **Snippet 1:**
   
   1. **Snippet 1:**
   

1. In the `main` method, write a few lines of code to test your method.
   In your notes, write how you know it is properly working. Try an example
   where you think the types of the actual parameters will work and one where
   you expect it not to work.

1. **Compile all of the code, then run it.** If you run into any issues,
   then revisit either your `fill` implementation or the test code you
   included in your `main` method. **Be sure that everything compiles
   and runs as expected before continuing.**
   
1. Now that everything on this branch compiles, ensure that all changes 
   in the current branch have been staged and committed. 

**CHECKPOINT**

1. The combination of options for the `git adog` command that you created earlier
   was popularized by a StackOverflow user named [Patoshi パトシ](https://stackoverflow.com/users/1642231/patoshi-パトシ)
   in their [reply to another user's post](https://stackoverflow.com/a/35075021).  
   
   ![ADOG](https://i.stack.imgur.com/ElVkf.jpg)
   
1. The StackOverflow user [fracz](https://stackoverflow.com/users/878514/fracz) suggested the `adog` alias
   that you setup earlier in their reply to Patoshi's reply.

**NOT A CHECKPOINT**
