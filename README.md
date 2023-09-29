# Monolisp

A lisp-based programming language written for a CompSci class. The name is a play on the word "monolith" and initially referred to the codebase.

## Examples

### Fibonacci sequence

The program asks the user for a number, and displays `N` Fibonacci numbers starting from 0.

```
(setvar n (num (input "Enter a number: ")))

(println "Finding" n "numbers of Fibonacci")

(setvar a 0)
(setvar b 1)
(setvar tmp 0)

(for i (range 0 n)
  (print a "")
  (setvar tmp a)
  (setvar a (+ a b))
  (setvar b tmp)
)

(println)
```

### FizzBuzz

Shows the FizzBuzz sequence from 1 to 100.

Since logical operators don't exist right now, the final if-statement uses multiplication to check if neither are 0.

```
(for i (range 1 101)
  (if (== (% i 3) 0)
    (print "Fizz")
  )

  (if (== (% i 5) 0)
    (print "Buzz")
  )

  (if (!= (* (% i 3) (% i 5)) 0)
    (print i)
  )

  (println)
)
```

## How to use

Make sure you have Java installed. If one version doesn't work, use a later one.

Once you get that set up, compile the Java source files into class files.

```
javac *.java **/*.java
```

Then make sure your program is in `program.mono` (no CLI interface yet) and type the following to run it.

```
java Monolisp
```

## Known quirks

### Missing features

- No logic operators: `not`, `xor`, `or` are missing.
- No control flow commands: `break`, `continue` are missing.

### Prod-unreadiness

- Operators intentionally work with only one argument, but should ideally be enforced to a minimum of 2.
- Not all Java errors are handled.
- Types are assumed in many places. For example, `==` only works with numbers.
- No comments, both in the codebase and in the language.
- User-defined functions are still to be defined.
- It's written in Java.
