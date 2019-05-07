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

Clojure recognize two kinds of structure, which are literals and operators. In a program, literals are manipulated by operations to produce new data. Those operations are called forms. The basic structure of a form or operation look like this: (operator operand operand operand ...).
The number of operator depend on the number of arguments an operator can support.

## Control Flow

   ### if
"if" evaluation is truthy the form after the expression is evaluted and returned, else the next expression is evaluted and returned. Those position matter, cause when you have only 3 elements in the form and the evaluation produce a falsey then nil is returned. Passing less than 3 a Too few arguments error is thrown. Keep in mind that there are only two falsey values in clojure: false and nil. false is java Boolean/FALSE under the wood.

```(if boolean-form
    then-form
    optional-else-form)

   (if (Boolean/FALSE) true false) => false
       (if (Boolean. false) true false) => true```

### do
When need to evaluate  more than one form in case of falsey or truthy, you need to use the "do" operator to wrap those forms.

(if boolean-form
    (do then-form-one
        then-form-two)
    (do optional-else-form-one
        optional-else-form-two))


### when
Use "when" as a shorcut for using "if" and "do". "when" true, everything after the condition is executed, if false then "nil" is returned. So it's good to use it always when you don't care about the else and will accept "nil" as return in case of falsey evaluation.

``` clojure
(when true
  (println "Success!")
  "Maflany")
; => Success!
; => "Maflany"
```

### Equality =
In clojure use  "=" symbol for equality checking. It perfom the checking base on the value, not by reference. If you pass only one argument to equal oparator, the result will always be true.

``` clojure
(= nil nil)
; => true
(= 1 "1")
; => false
(= nil)
; => true
```

# Boolean operators or and
### or
Return the first truthy value, or the last value.

``` clojure
(or false :a :b :c)
; => :a

(or false nil :b :c)
; => :b

(or false nil false nil)
; => nil
```

### and
Return the first falsey value or if no values are falsey return the last value.

``` clojure
(and false true "Maflany" "")
; => false

(and true true "Maflany" "")
; => ""
```


## def
Bind a name to a value.

## Data Structure
Important thing to know, clojure data structures are immutable.

### Numbers
Clojure numbers are the same like in others programming languages, but has ratio too. so if you do (/ 6 10) ===> 3/5.
This is very useful if you need to compute ratio numbers. It gives you more precision.

### String
Clojure strings are declared with double quote. There is no string interpolation, and concatenation operations are performed with "str" function.

### Maps
Clojure has `hash-map` and `sorted-map`. They are key <=> value associations. Something cool about clojure maps is that keys and values can be almost anything.
`hash-map` criations: {}, {key1 value1 key2 value2}, (hash-map key1 value1 key2 value2)
Tips: In maps comparison, keys=>values position doesn't matter.
```clojure
(= {3 4 1 2} {1 2 3 4}) => true
```

### Keywords
They can be any clojure valid var name preceeded by ":" and are primarely used as keys in maps. Let say you have a map representing a person: (def person {first-name "name" last-name "last-name"}) ===> this will throw an "Unable to resolve symbol" error cause compiler will try to resolve keys first-name and last-name. But if use keywords, you get out of this trouble cause they are resolved by themselves. So this will work (def person {:first-name "name" :last-name "last-name"}).


### Symbols
They are name, they refer to something else. Used to refer to things like variables, functions names.

### Vectors
Vectors are kind of array representation in clojure. They are indexed, so accessing element take `log32N`. Grow at the end.  Take any clojure forms, literals and data.
Try the forms below in the repl to see the output:

``` clojure
[] ===> Empty vector
["string" {} 1 :a] ===> Take any clojure forms, literals and data
(vector)           ===> same with []
(vector 1 2 3 4)   ===> [1 2 3 4]
(get [1 2 3 4] 0)  ===> 1
(conj [1 2 3 4] 5) ===> [1 2 3 4 5]
```

### Lists
Singly linked, which means  access to any element has tranversal cost. New elements are inserted at the front. Lists elements can be any clojure literals, forms and data.
Access to lists elements is done by using `nth`, use `get` will always return `nil`.

``` clojure
'()                ===> ()
(list 1 2 3)       ===> (1 2 3)
(nth '(1 2 3) 0)   ===> 1
(get '(1 2 3) 0)   ===> nil
(conj '(1 2 3) 4)  ===> (4 1 2 3)
```

### Sets
Clojure has two kind of sets: `hash-set` and `sorted-set`
Every element in a set is unique. You can look for an object in a set by using get keyword or contains?. get and keywords will return the element if found else will return nil. contains? will tell you if an element exist in the set or not by returning true or false. Be careful when using get and keywords on sets, cause you could easily get confuse if there is a nil among the elements in the set.
The main difference between `hash-set`and `sorted-set` is sorting element perform by the second one.
Maps are also 
``` clojure
(hash-set 1 1 2 2)           ===> #{1 2}
(get #{1 2 nil} nil)         ===> nil
(:a #{1 2 :a})               ===> :a
(contains? #{1 2 nil} nil)   ===> true
(#{1 2 :a} :a)               ===> :a ;;In clojure, data structure tends to act like functions.
```

Tips: Vectors, lists, maps, sets are all nestable.

### Functions
Functions are everywhere in closure, complexes systems are build by calling functons. To call a function you need to use it as operator follow by operand(s). The operator can be anything which implement the clojure function interface(IFn). Clojure has higher-order functions, which means take function as arguments or/and return another function.
Function declaration can contains five parts:



``` clojure
defn (Symbol use to declare a function)
function name (it's not mandatory, but whitout name your function won't be reusable)
docstring (function description goes here, very handy to get info about the function. it's not mandatory)
parameter(s) lists in brackets, if no parameter then should be empty brackets
function body (here goes the function implementation)

(defn sample-function
    "function description"
    [parameters]
    function-body-goes-here)
```
Clojure function support overloading. The function has different body. According to the number of parameters passed to the function when calling it, the corresponding body will be called.

``` clojure
(defn multi-arity
    ([one-argument]
        (str one-argument))
     ([one-argument second-argument]
        (str one-argument second-argument))
     ([one-argument second-argument third-argument]
        (str one-argument  second-argument third-argument))
```
Example:

``` clojure
(defn concat-them
    ([one]
        (str one))
    ([one two]
        (str one two))
    ([one two three]
        (str one two three)))

(concat-them "one")               ==> "one"
(concat-them "one" "two")         ==> "onetwo"
(concat-them "one" "two" "three") ==> "onetwothree"
```
Calling a function with wrong arity will throw an ArityException.

``` clojure
(concat-them) ==> ArityException Wrong number of args (0) passed to: user/concat-them  clojure.lang.AFn.throwArity (AFn.java:429)
```
Clojure allow you to define variable arity called rest parameters, for functions using the ampersand (&). Those parameters are put in a list.

``` clojure
(defn variable-arity
    [& names]
    (apply str names))

(variable-arity "joe" "bill" "kid") ==>  "joebillkid"
```
It's possible to use rest parameters with normal ones, but the rest parameter always has to come last.

### Destructuring
It's an easy way to bind name to value from a collection passed to a function.

#### Sequential destructuring
This kinf of destructuring is perform on sequential datastructures.

``` clojure
(defn destructuring
    [[first-param]]
    first-param)

(destructuring [1 2 3]) ==> 1

(defn destructuring
    [[first-param second-param]]
    (str first-param second-param))

(destructuring [1 2 3 4]) ==> "12"

(defn destructuring
    [[first-param second-param & other-parameters]]
    (prn first-param second-param other-parameters))

(destructuring ["first" "second" "third" "fourth"]) ==> "first" "second" ("third" "fourth")
```

It`s worth to notice that if a string is passed to a function and try to use array destructuring, then the string is treated as an array and each characters will be assign to a variable.
Passing string or clojure sequential collections will work fine. But the use of antyhing else to perform sequential destructuring will throw an `UnsupportedOperationException`. Rest can also be used for destructuring. 

``` clojure
(defn binding-function-test
    [[first-param second-param]
    (prn first-param second-param))

(binding-function-test "test")    ==>  \t \e
                                       nil
```

#### Destructuring Maps
Destructuring maps data structures look a bit like sequential ones, with a slightly difference. A map is used to perfom value binding.

``` clojure
(defn binding-function-test
    [{lat :lat lg :lg}]
    (prn lat lg))
```
