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

###Forms
1) Literal representation of data structures(Likes numbers, strings, maps and vectors)
2) Operations

Forms refer to valid clojure code, they are evaluted to data. 
LITERALS are manipulated by OPERATIONS to produce new data. 
Literals and Operations interaction take place in a parentheses: (operator operand1 operand2 ... operandn). 
Spaces are commas and vice-versa.


## Control Flow
In this chapter, if, do and when are introduced. But they are not the only ones, later on in the book we'll get to know the others.

    ### 1) if
    A boolean is evaluted, if truthy the third form evalution is returned, else the fourth one evalution is returned. Those position matter, cause when you have only 3 elements in the form and the evaluation produce a falsey then nil is returned. Passing less than 3 a Too few arguments error is thrown.

    (if boolean-form
        then-form
        optional-else-form)
    
    ### 1) do
    When need perform more than one form when truthy or falsey, you need to use a "do" operator to wrap those forms.

    (if boolean-form
        (do then-form-one
            then-form-two)
        (do optional-else-form-one
            optional-else-form-two))
    
    