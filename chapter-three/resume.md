# What did i learn?

## Clojure important caracteristics:
1) Lisp Dialect
2) Primarily Pure Functional
3) Emphasize on Concurrency
4) Designed to be hosted

## Lisp Core Elements
1) Syntax
2) Functions
3) Data

## Syntax

### Forms
1) Literal representation of data structures(Likes numbers, strings, maps and vectors)
2) Operations

Forms refer to valid clojure code, they are evaluted to data. 
LITERALS are manipulated by OPERATIONS to produce new data. 
Literals and Operations interaction take place in a parentheses: (operator operand1 operand2 ... operandn). 
Spaces are commas and vice-versa.


## Control Flow
In this chapter, if, do and when are introduced. But they are not the only ones, later on in the book we'll get to know the others.

### if
A boolean is evaluted, if truthy the third form evalution is returned, else the fourth one evalution is returned. Those position matter, cause when you have only 3 elements in the form and the evaluation produce a falsey then nil is returned. Passing less than 3 a Too few arguments error is thrown.

(if boolean-form
    then-form
    optional-else-form)
    
### do
When need perform more than one form when truthy or falsey, you need to use a "do" operator to wrap those forms.

(if boolean-form
    (do then-form-one
        then-form-two)
    (do optional-else-form-one
        optional-else-form-two))


### when
Use "when" as a shorcut for using "if" and "do". "when" it's evaluated to true everything after the condition is executed. If not truthy and the "nil" is return. So it will be good to use "when" always when you don't care about the else and will accept "nil" as return in case of falsiness of the condition evaluation.

(when true
  (println "Success!")
  "Maflany")
; => Success!
; => "abra cadabra"

NB: In clojure only "nil" and "false" are falseys, anything else is truthy.


### Equality =
In clojure equality is resolve by "=" operator, no matter operands type.

(= 1 "1")
; => false


# Boolean operators or and
or return the first truthy value, or the last value.

(or false :a :b :c)
; => :a

(or false nil :b :c)
; => :b

(or false nil false nil)
; => nil

and return the first falsey value or if no values are falsey return the last value.

(and false true "Maflany" "")
; => false

(and true true "Maflany" nil)
; => nil
