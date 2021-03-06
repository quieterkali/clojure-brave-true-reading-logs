# What did I learn?

1 - Clojure has been created by Rich Hickey

2 - Important thing to know about clojure:
    -- Clojure the lisp dialect programming language with functional emphasis.
    - Clojure the Compiler, an executable jar file which compile clojure code to java byte code.
    - So keep that in mind when reference is made to clojure.

3 - Little recap about how Clojure and JVM interact
    - JVM execute Java byte code
    - Usually, Java Compiler produce Java bytecode from Java source code.
    - JAR files are all collections of Java bytecode
    - Java programs are usually distributed as jar files.
    - The java program clojure.jar reads clojure source code and produce java bytecode.
    - That Java bytecode is then execute by the same JVM process already running clojure.jar

4 - Leiningen is the most used tools for building and managing clojure projects.
    - Create new Clojure project:
        lein new <<type of project>> <<project-name>>:
            lein new app clojure-noob
    - Project structure

        | .gitignore
        | doc
        | | intro.md
        ➊ | project.clj         --> Leiningen configuration file, list project dependencies
        | README.md
        ➋ | resources           --> Where store Assets
        | src
        | | clojure_noob
        ➌ | | | core.clj        --> Where start writing code.
        ➍ | test                --> Where write tests.
        | | clojure_noob
        | | | core_test.clj

5 - Some Leiningen commands
     - lein run: To run app
     - lein uberjar: To generate project standalone file. A jar file to be execute like any java jar.
     - lein repl: To run project in repl mode, useful to test code, call functions, 
       have a quick feedback and understanding of what is going on.
       
