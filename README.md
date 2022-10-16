# casagemas

Collection of clerk viewers supporting `rendering-hints` in metadata

The buildin Clerk viewer selection is based on either a function call and giving / naming a viewer
or on metadata which expresses the same, namely the name of a Clerk viewer to choose.

This repo is an experiment, to see if "Clerk viewer selection" can be soly based on the "metadata" of the value to render .

We define below how Clerk can be hinted about the "type of data" a value has,
and then we do the right think, so render it as good as we can.

So instead of telling Clerk "which viewer" we tell "which type of data" and it picks the viewer automatically.

So far casagemas supports the below listed hints, which matches with the build-in viewers of Clerk + some more.
So if for example, a piece of data (like a map) has metadata:

``` clojure
{:org.scicloj.rendering-hint :vega.github.io/vega-lite}
```

casagemas will do its best to render it, which will be using the Clerk build-in vega-lite viewer.

This library is expected to grow over time and support far more data types and custom viewers.
PRs are welcome.

Other graphical tools are invited to do the same, and do their best to render a hinted value accordingly.

## rendering hints

### Global table
Global table of well known rendering hints (***to be published somewhere else, not here***)

| key/predicate/class                                        | value class / type                   | definition                     |
|------------------------------------------------------------|--------------------------------------|--------------------------------|
| tech.v3.dataset/dataset?                                   | tech.v3.dataset.impl.dataset.Dataset | tech.ml.dataset instance       |
| :org.scicloj.rendering-hint :vega.github.io/vega-lite       | :map                                 | vega lite data spec            |
| :org.scicloj.rendering-hint :mermaid-js.github.io/mermaid   | :string (wrapped in map)             | mermaid spec                   |
| :org.scicloj.rendering-hint :latex-project.org/latex        | :string (wrapped in map)             | latex expression               |
| :org.scicloj.rendering-hint :github.com/plotly              | :map                                 | plotly data spec               |
| :org.scicloj.rendering-hint :cytoscape.org/cytoscape        | :map                                 | cytoscape data spec            |
| :org.scicloj.rendering-hint :kroki.io/kroki                 | :string (wrapped in map)             | kroki supported string         |
| :org.scicloj.rendering-hint :nextjournal/markdown           | :string (wrapped in map)             | Nextjournal markdown spec      |
| :org.scicloj.rendering-hint :github.com/stathissideris/dali | :map                                 | Dali spec                      |
| :org.scicloj.rendering-hint :github.com/JonyEpsilon/gg4clj  | :map                                 | gg4cl spec                     |
| :org.scicloj.rendering-hint :clojure/table                  | :map                                 | regular table-like clojure map |
| :org.scicloj.rendering-hint :code                           | :string (wrapped in map)             | source code                    |

### supported by **this** viewer  using Clerk
Table of rendering hints currently supported by the casegamas viewer for Clerk

| key/predicate/class                                      | value class / type                   | definition |   |
|----------------------------------------------------------|--------------------------------------|------------|---|
| tech.v3.dataset/dataset?                                 | tech.v3.dataset.impl.dataset.Dataset |            |   |
| :org.scicloj.rendering-hint :vega.github.io/vega-lite     | :map                                 |            |   |
| :org.scicloj.rendering-hint :mermaid-js.github.io/mermaid | :string (wrapped in map)             |            |   |
| :org.scicloj.rendering-hint :latex-project.org/latex      | :string (wrapped in map)             |            |   |
| :org.scicloj.rendering-hint :github.com/plotly            | :map                                 |            |   |
| :org.scicloj.rendering-hint :cytoscape.org/cytoscape      | :map                                 |            |   |
| :org.scicloj.rendering-hint :kroki.io/kroki               | :string (wrapped in map)             |            |   |
| :org.scicloj.rendering-hint :nextjournal/markdown         | :string (wrapped in map)             |            |   |
| :org.scicloj.rendering-hint :clojure/table                | :map                                 |            |   |
| :org.scicloj.rendering-hint :code                         | :string (wrapped in map)             |            |   |

If none of the predicates match, the usual Clerk viewer selection is used
and the value is rendered as default by Clerk.


## usage

This library contains a collection of Clerk viewers definition.
This libraries does not declare dependencies in deps.edn to any lib it might require.


The viewer get activated by standard metadata of a var, see this list (http://xxxxx)



The following code in the repl adds the viewers of casagemas to Clerk, for all namespaces.
Attention: adding the viewers from a ns `requires` the ns. For some of them this expects 
specific libs on the classpath. See the code to discover which are needed.
This will be documented better soon.

```clojure
(nextjournal.clerk.viewer/reset-viewers!
   ;; :default
   (find-ns 'nextjournal.clerk.tap)
   (-> nextjournal.clerk.viewer/default-viewers
       (nextjournal.clerk.viewer/add-viewers [nextjournal.clerk.tap/tap-viewer])
       (nextjournal.clerk.viewer/add-viewers
        (concat (org.scicloj.casagemas/viewers)
                (org.scicloj.casagemas.dataset/viewers)
                (org.scicloj.casagemas.kroki/viewers)))))
```



## License

Copyright © 2022 Carsten Behring

_EPLv1.0 is just the default for projects generated by `clj-new`: you are not_
_required to open source this project, nor are you required to use EPLv1.0!_
_Feel free to remove or change the `LICENSE` file and remove or update this_
_section of the `README.md` file!_

Distributed under the Eclipse Public License version 1.0.
