(ns notebook
  (:require [tech.v3.dataset :as ds]))

;; In this notebook we give rendering hints to the values,
;; and then a suitable render is picked automatically
;; (or the default Clerk rendered,
;; when the rendering hint is not supported.

;; ## vega lite

^{:org.scicloj/rendering-hint :vega.github.io/vega-lite}

{:width 200
 :height 200
 :data {:url "https://vega.github.io/vega-datasets/data/us-10m.json"
        :format {:type "topojson" :feature "counties"}}
 :transform [{:lookup "id"
              :from {:data {:url "https://vega.github.io/vega-datasets/data/unemployment.tsv"}
                     :key "id"
                     :fields ["rate"]}}]
 :projection {:type "albersUsa"}
 :mark "geoshape"
 :encoding {:color {:field "rate"
                    :type "quantitative"}}}

;; Clerk annotations are as well supported

^{:org.scicloj/rendering-hint :vega.github.io/vega-lite
  :nextjournal.clerk/width :full}
{:width 400
 :height 400
 :data {:url "https://vega.github.io/vega-datasets/data/us-10m.json"
        :format {:type "topojson" :feature "counties"}}
 :transform [{:lookup "id"
              :from {:data {:url "https://vega.github.io/vega-datasets/data/unemployment.tsv"}
                     :key "id"
                     :fields ["rate"]}}]
 :projection {:type "albersUsa"}
 :mark "geoshape"
 :encoding {:color {:field "rate"
                    :type "quantitative"}}}


;;  ## mermaid

^{:org.scicloj/rendering-hint :mermaid-js.github.io/mermaid}
{:spec
 "stateDiagram-v2
    [*] --> Still
    Still --> [*]
    Still --> Moving
    Moving --> Still
    Moving --> Crash
    Crash --> [*]"}


;;  ## latex
^{:org.scicloj/rendering-hint :latex-project.org/latex}
{:spec "G_{\\mu\\nu}\\equiv R_{\\mu\\nu} - {\\textstyle 1 \\over 2}R\\,g_{\\mu\\nu} = {8 \\pi G \\over c^4} T_{\\mu\\nu}"}

;; ## tech.ml.dataset

(ds/->dataset {:x [:a :b :c :d]
               :y [1 2 3 4]})

;;  ## kroki
^{:org.scicloj/rendering-hint :kroki.io/kroki
  :diagram-type :plantuml}
{:spec
 "
@startuml
Alice -> Bob: Authentication Request
Bob --> Alice: Authentication Response

Alice -> Bob: Another authentication Request
Alice <-- Bob: Another authentication Response
@enduml
"}

;; ## cytoscape

^{:org.scicloj/rendering-hint :cytoscape.org/cytoscape}
{:elements {:nodes [{:data {:id "a" :parent "b"} :position {:x 215 :y 85}}
                    {:data {:id "b"}}
                    {:data {:id "c" :parent "b"} :position {:x 300 :y 85}}
                    {:data {:id "d"} :position {:x 215 :y 175}}
                    {:data {:id "e"}}
                    {:data {:id "f" :parent "e"} :position {:x 300 :y 175}}]
              :edges [{:data {:id "ad" :source "a" :target "d"}}
                      {:data {:id "eb" :source "e" :target "b"}}]}
   :style [{:selector "node"
            :css {:content "data(id)"
                  :text-valign "center"
                  :text-halign "center"}}
           {:selector "parent"
            :css {:text-valign "top"
                  :text-halign "center"}}
           {:selector "edge"
            :css {:curve-style "bezier"
                  :target-arrow-shape "triangle"}}]
   :layout {:name "preset"
            :padding 5}}




;; ## markdown
^{:org.scicloj/rendering-hint :nextjournal/markdown}
{:spec "1. Lambda\n2. Eval\n3. Apply"}

;; ## table
^{:org.scicloj/rendering-hint :clojure/table}
{:col/a [1 2 3 4] :col/b [1 2 3] :col/c [1 2 3]}

;; ## code
^{:org.scicloj/rendering-hint :code}
{:spec "(+ 1 2)"}

;; ## html
^{:org.scicloj/rendering-hint :html}
[:h1 "Ohai Hiccup 👋"]

;; ### function calls require `with-meta`

;; Asigning meta-data via ^ is only supported or literals
;; in Clojure
;; if we have functions which return data, the metadata cannot
;; be attached via code whch calls a function

(defn make-html []
  [:h2 "Ohai Hiccup"])

(with-meta
  (make-html)
  {:org.scicloj/rendering-hint :html})
  

;; ### or "inside function"
(defn make-html-inline []
  ^{:org.scicloj/rendering-hint :html}
  [:h2 "Ohai Hiccup"])

(make-html-inline)



;;  Clerk does some magic with `def`,
;;  so this is working:

(def h
  ^{:org.scicloj/rendering-hint :html}
  [:h3 "html on def"])

;; This does not
^{:org.scicloj/rendering-hint :html}
(make-html)


;; -> This is how Clojure metadata works.

;; ## default rendering on unknown hints
;;  the following is rendered as map,
;;  as rendering hint is not supported

^{:org.scicloj/rendering-hint :vega.github.io/not-supported-rendering
  :nextjournal.clerk/width :full}
{:width 100
 :height 100
 :data {:url "https://vega.github.io/vega-datasets/data/us-10m.json"
        :format {:type "topojson" :feature "counties"}}
 :transform [{:lookup "id"
              :from {:data {:url "https://vega.github.io/vega-datasets/data/unemployment.tsv"}
                     :key "id"
                     :fields ["rate"]}}]
 :projection {:type "albersUsa"}
 :mark "geoshape"
 :encoding {:color {:field "rate"
                    :type "quantitative"}}}

;; ## overide default viewer selection
;; By inventing a new rendering-hit + adding a custom viewer with
;; :pred which is matching the rendering-hint
^{:org.scicloj/rendering-hint :code}
{:spec "
{:pred (fn [v] (= :my-custon-hint
                    (:org.scicloj/rendering-hint (meta v))))
    :transform-fn ..whatever..
    :render-fn ..whatever.. }

(clerk/add-viewer ...)

....
....

^{:org.scicloj/rendering-hint :my-custom-hint}
{:my-map :to-render-special}
"}
(comment
  ;;  for testing only
  (require  '[nextjournal.clerk :as clerk]
            '[nextjournal.clerk.viewer :as v]))

  ;; (clerk/clear-cache!)
