# casagemas

FIXME: my new library.

## Usage

Global table of well known rendering hints (published somewhere else)

| key/predicate/class                  | value class / type                       | definition |
|----------------------------------    |----------------------------------------- | ---------- |
|tech.v3.dataset/dataset?              |  tech.v3.dataset.impl.dataset.Dataset    |            | 
|org.scicloj.rendering-hint :vega-lite |  map                                     |
|org.scicloj.rendering-hint :mermaid   |  :string                                 |            |
|....  | ... ... |...


Table of well known rendering hints currently supported by the casegamas renderer for Clerk

| key/predicate/class                  | value class / type                       | definition |
|----------------------------------    |----------------------------------------- | ---------- |
|tech.v3.dataset/dataset?              |  tech.v3.dataset.impl.dataset.Dataset    |            | 
|org.scicloj.rendering-hint :vega-lite |  map                                     |
|org.scicloj.rendering-hint :mermaid   |  :string                                 |            |




Invoke a library API function from the command-line:

    $ clojure -X org.scicloj.casagemas/foo :a 1 :b '"two"'
    {:a 1, :b "two"} "Hello, World!"

Run the project's tests (they'll fail until you edit them):

    $ clojure -T:build test

Run the project's CI pipeline and build a JAR (this will fail until you edit the tests to pass):

    $ clojure -T:build ci

This will produce an updated `pom.xml` file with synchronized dependencies inside the `META-INF`
directory inside `target/classes` and the JAR in `target`. You can update the version (and SCM tag)
information in generated `pom.xml` by updating `build.clj`.

Install it locally (requires the `ci` task be run first):

    $ clojure -T:build install

Deploy it to Clojars -- needs `CLOJARS_USERNAME` and `CLOJARS_PASSWORD` environment
variables (requires the `ci` task be run first):

    $ clojure -T:build deploy

Your library will be deployed to org.scicloj/casagemas on clojars.org by default.

## License

Copyright © 2022 Carsten Behring

_EPLv1.0 is just the default for projects generated by `clj-new`: you are not_
_required to open source this project, nor are you required to use EPLv1.0!_
_Feel free to remove or change the `LICENSE` file and remove or update this_
_section of the `README.md` file!_

Distributed under the Eclipse Public License version 1.0.
