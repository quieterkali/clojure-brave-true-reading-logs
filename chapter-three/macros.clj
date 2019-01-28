(defmacro if-not
  ([test then] `(if-not ~test ~then nil))
  ([test then else]
   `(if (not ~test) ~then ~else)))

(if-not false :true)






Daouda [1:17 AM]
hey guys, i am playing at trying to understand some clojure source code, and i need help to understand the `or` implementation. can you guys go through each line of the `or` body and explain it to me, please
```(defmacro or
  "Evaluates exprs one at a time, from left to right. If a form
  returns a logical true value, or returns that value and doesn't
  evaluate any of the other expressions, otherwise it returns the
  value of the last expression. (or) returns nil."
  {:added "1.0"}
  ([] nil)
  ([x] x)
  ([x & next]
      `(let [or# ~x]
         (if or# or# (or ~@next)))))```
i tried to evaluate `([] nil)` in the repl but that throws an error
for some how, i think be able to read the source code and understand it can help me to get a better understanding of clojure

john [1:21 AM]
That has an implementation for 3 different "arities"
Zero arity: ([] nil)
1 arity: ([x] x)
Does that much make sense? So far?

Daouda [1:23 AM]
yeah yeah
just googled arities hehehehe (edited) 
arities = arguments?

john [1:24 AM]
So yeah, four functions or macros you can provide any number of arity implementations. That there is a macro, so it's going to have some more complex syntax
Right, but specifically the word arity is with regard to the _number_ of arguments
We say a thing has zero arity, two arity, or many arity

Daouda [1:26 AM]
i understand

john [1:26 AM]
If that much makes sense, what else seems mysterious about it still? The `if`? The `let` binding?

Daouda [1:26 AM]
yeaaaahhhhh
look like you're in my thinkings

john [1:27 AM]
A lot of the extra punctuation is there because it's a macro. Macros are functions that write other functions, like templates

Daouda [1:28 AM]
yeah i read that they are kind of functions but are not evaluated or something like that
but i honestly didn't get to dig on them yet

john [1:29 AM]
They're super powerful

Daouda [1:30 AM]
`let [or# ~x]` putting `~x`  into `or#`

john [1:30 AM]
But easier to understand once you understand the syntax for _functions_

Daouda [1:30 AM]
but why not just `x`
`~` means what here?
i think i understand a little bit functions

john [1:33 AM]
Right, those are special characters for macros. `~` is an "unquote"

Daouda [1:33 AM]
functions `defn function name args body`

john [1:33 AM]
```

didibus [1:33 AM]
`(or 1 2)` becomes:

```(let [v 1]
  (if v
    v
    (or 2)))```

If that helps (edited) 

john [1:33 AM]
`
That's a syntax quote
Everything inside: `(inside stuff here)

Daouda [1:35 AM]
what else if i have `(or false false false (prn foo))`?
what will be that be in the `if` call?
ooooooh sorry
you edited the answer

didibus [1:36 AM]
Ya, I'm on my phone, so its hard to type :yum: I edited it
So if you have more things the else would be the rest (edited) 
```(let [v 1]
  (if v
    v
    (or 2 3 false (print 23) true false)))```
And then it will recursively expand
Into a bunch of nested if that are similar to this

john [1:37 AM]
That or would return to nil from the last println, because it'll keep calling itself until it gets a truthy value or runs out of things (edited) 

didibus [1:38 AM]
Until you reach an else with a single element in the or like `(or false)` and that will call the one arity or, which just returns the value passed in

john [1:39 AM]
Inside a quoted form, an unquote symbol will grab the thing from the outside, compiling environment.

Daouda [1:39 AM]
so `(or ~@next)` will be this `(or 2 3 false (print 23) true false)`?

didibus [1:40 AM]
Yup

Daouda [1:40 AM]
cool

john [1:40 AM]
Sorta like string interpolation
But in this case recursive string interpolation

Daouda [1:42 AM]
sorry @john please show me the quoted and unqutoted form in the code i pasted please
sorry for asking too much but really want to make sure i get what you are saying

didibus [1:43 AM]
So `(or 1 2 3)` after all expansions becomes:

```(let [v 1]
  (if v
    v
    (let [g 2]
      (if g
          g
          3))))```

Daouda [1:43 AM]
`Inside a quoted form, an unquote symbol will grab the thing from the outside, compiling environment.`

john [1:46 AM]
So like, in the `or` form:
```(defmacro or
  ([] nil)
  ([x] x)
  ([x & next]
      `(let [or# ~x]                            ;; <- syntax quote starts on this line with the ` symbol
         (if or# or# (or ~@next)))))    ;; <- a syntax unquote starts on this one with the ~ symbol```
`~@` is a "splicing unquote," which will remove the parenthesis from around whatever's being unquoted
the `#` symbol attaches some extra metadata to the `or`variable so that it does not clash with other instances of or variables that may be recursively defined, so that it doesn't clobber itself
usage of the word "or" for that variable in the let binding is probably unhelpful from an understanding perspective
It could just have easily been called `x#`
or, not x

Daouda [1:49 AM]
is it possible to show me a snippet code of `~@` in action please?

john [1:50 AM]
`y#` perhaps, since `x` is already being used in the outside context, which would just add to the confusion :laughing:

Daouda [1:51 AM]
something like `(~@ apply on something) ===> something else`

john [1:53 AM]
It's normally called from within a macro... and specifically, from within a form that is being syntax quoted

Daouda [1:55 AM]
yeah i just googled and found some reading about it :slightly_smiling_face:
thank you very much @john and @didibus, you've taught me a lot in such a short time :slightly_smiling_face:

john [1:58 AM]
also study the `when` macro:
```(defmacro when
  [test & body]
  (list 'if test (cons 'do body)))```

```(defmacro when
     "Evaluates test. If logical true, evaluates body in an implicit do."
     {:added "1.0"}
     [test & body]
     (list 'if test (cons 'do body))) ===> (if test (do body))

(when true (prn "Maflany" (prn 1) (prn 2) (prn 3)))

=> (list 'if true (cons 'do (prn "Maflany" (prn 1) (prn 2) (prn 3))))

=> (if true (do (prn "Maflany" (prn 1) (prn 2) (prn 3))))```




that's building up the template in a different way

Daouda [2:00 AM]
that is exactly the one i was planning to learn after `or` :smile: (edited) 

john [2:01 AM]
also check out `if-not`:
(defmacro if-not
  ([test then] `(if-not ~test ~then nil))
  ([test then else]
   `(if (not ~test) ~then ~else)))
That may be an easier one to start off with

Daouda [2:03 AM]
ok, but i warn you that questions will come :smile:

john [2:06 AM]
Well there's plenty of folks around here willing to help

Daouda [2:07 AM]
:smile: just giving those tips about where to start looking help a lot

john [2:08 AM]
Just remember, everything inside a `()` is a form. Everything inside a syntax quoted form ``()` is a like a _future form_. It turns into a present form after being evaluated once. Anything _unquoted_ within a quoted future form, will be injected into the future form from the present form. (edited) 
well... It's hard to show a syntax quote here :slightly_smiling_face:
`()
That's a syntax quoted form
so, in the above `if-not` macro, test, then and else are all existing as their present values in the present macro form
the if and the not, within the syntax quoted form, do not exist as present values... you can think of them like strings
everything between the ` and the ~ is like a string. Everything inside the form or value to the right of the unquote symbol will be pulled out of the _present_ form and written into the template, like concatenating present and future values together.
so ~test, ~then and ~else are pulling the _present_ values and pasting them into a string that will become a form that looks like an if statement with the test cond negated.
(it's not actually a string, but you get the idea)

Daouda [2:17 AM]
@john :clap::+1::grinning::heavy_check_mark: you're explanation did really help, i am going to keep it somewhere close and read it again again again again :smile:

john [2:17 AM]
Good deal, keep reading the code too, again and again and again :slightly_smiling_face:

Daouda [2:17 AM]
yeah i got the idea, the code make more sense now to me

---------------------------------------------------------------------------------------------


kenj [1 :38 AM]
I read one of the main benefits of transducers is that it eliminates intermediate collection values between steps. If each of your steps returns a lazy seq though, are intermediate values really a big deal?

chrisulloa [1 :45 AM]
So `(->> [1 2 3] (map foo) (map bar)) `returns a lazy sequence and creates an intermediate sequence in the thread-macro `((foo 1) (foo 2) (foo 3)) `but `(transduce (comp (map foo) (map bar)) + [1 2 3]) `will pass values in one by one through the `comp `defined then pass them into the reducing function `+ `(edited)
`map `returns lazy seqs when a collection is passed into it `(map foo [1 2 3]) `but without it is a transducer `(map foo) `With `transduce `you’re only really interested in composing transducers, so functions that only ever return lazy seqs won’t work.
Just to clarify `(->> [1 2 3] (map foo) (map bar)) `is really the same as `(map bar (map foo [1 2 3])) `kenj [1 :51 AM]
I suppose that would be helpful if you have a transformation step that isn’t lazy and didn’t want to realize a really large intermediate, or just wanted to keep things as fast as possible in a hot loop?

chrisulloa [1 :53 AM]
Yeah I think a big benefit to using a transducer is if you have a really large collection, and are only really interested in reducing the dimension of the data to a number or string or something `[1 2 3] => 6 `though you can also keep dimension by doing `(into [] ...) `. Avoiding intermediate sequences will help performance in either case. (edited)
Transducers are also really good if you need to create a composable data transformation that is independent of the input and output specifics
You can pass collections, streams, channels, observables as inputs/outputs, while the actual transducer functions only ever worry about what happens to one item at a time

kenj [2 :02 AM]
thanks, that makes a lot of sense

