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
### or
or return the first truthy value, or the last value.

(or false :a :b :c)
; => :a

(or false nil :b :c)
; => :b

(or false nil false nil)
; => nil

### and
and return the first falsey value or if no values are falsey return the last value.

(and false true "Maflany" "")
; => false

(and true true "Maflany" nil)
; => nil


## Naming values with def
Use "def" to create a global variable, treat it as it's defining a constant.

## Data Structure
Important thing to know, clojure data structures are immutable.
(Wait a minute, so what if i need to change something for some reason?)
(Well later on you'll learn how to achieve that, but know that clojure emphasize on immutability, and the more you'll follow that the happier you'll be in your life :D)

### Numbers
Clojure numbers are the same you've met or will meet in others programming language. But clojure has something more. It allow ratio. so if you do (/ 6 10) ===> 3/5.

### String
Clojure strings are declared with double quote and only! Doesn't have string interpolation, and concatenation operations are performed with "str" function.


### Maps
Clojure has hash maps and sorted maps. They are key <=> value data structure. But the cool thing about clojure maps is that keys and values can be almost anything. So i invite you to try anything to come to your mind and find out by your self if it work or not(Be curious :D).
Hash map criations: {}, {chave1 valor1 chave2 valor2}, (hash-map chave1 valor1 chave2 valor2)
See clojure documentation for maps APIs to know all what you can perform with them. Do that cause you will use them quite a lot :D.

### Keywords
They can be any clojure valid var name preceded by ":" and are primarely used as keys in maps. Let say you have a map representing a person: (def person {first-name "name" last-name "last-name"}) ===> this will throw an "Unable to resolve symbol" error cause compiler will try to resolve keys first-name and last-name. But if use keywords, you get out of this trouble cause they are resolved to themselves. So this will work (def person {:first-name "name" :last-name "last-name"}).