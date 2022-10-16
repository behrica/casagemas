(ns notebook)



^{:org.scicloj/rendering-hint :vega.github.io/vega-lite
  :nextjournal.clerk/width :full}
{:width 650
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

^{:org.scicloj/rendering-hint :mermaid-js.github.io/mermaid}
{:spec
 "stateDiagram-v2
    [*] --> Still
    Still --> [*]
    Still --> Moving
    Moving --> Still
    Moving --> Crash
    Crash --> [*]"}

^{:org.scicloj/rendering-hint :latex-project.org/latex}
{:spec "G_{\\mu\\nu}\\equiv R_{\\mu\\nu} - {\\textstyle 1 \\over 2}R\\,g_{\\mu\\nu} = {8 \\pi G \\over c^4} T_{\\mu\\nu}"}

^{:org.scicloj/rendering-hint :github.com/plotly}
{:data [{:z [[1 2 3] [3 2 1]] :type "surface"}]}



(ds/->dataset {:a ["first column"]})


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





^{:org.scicloj/rendering-hint :markdown}
{:spec "1. Lambda\n2. Eval\n3. Apply"}



(comment
  ;;  for testing only
  (require  '[nextjournal.clerk :as clerk]
            '[nextjournal.clerk.viewer :as v]))

  ;; (clerk/clear-cache!)
