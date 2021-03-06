/**
 * Answers to question 3 in ThinkAPJava chapter 15
 * http://www.greenteapress.com/thinkapjava/thinkapjava.pdf
 *
 * Question:
 * Transform the following object method into a class method.
     public boolean equals(Complex b) {
         return(real == b.real && imag == b.imag);
     }
 *
 * @author Rafi Long
 *
 * Edits by Rafi:
 * - created the new equals() that is static (see equals static method for documentation)
 */
public class Question3 extends Complex {

    /**
     * Original version
     */
    public boolean equals(Complex b) {
        return(real == b.real && imag == b.imag);
    }

    /**
     * Class method version
     *
     * Edits by Rafi:
     * 1) Added that static keyword
     * 2) Added a new Complex argument a
     * 3) Made variables that would be a reference to the this variable a.*
     */
    public static boolean equals(Complex a, Complex b) {
        return(a.real == b.real && a.imag == b.imag);
    }
}
