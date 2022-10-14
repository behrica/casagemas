(ns notebook
  (:require  [tech.v3.dataset :as ds]))



^{:org.scicloj/rendering-hint :vega-lite
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

^{:org.scicloj/rendering-hint :mermaid}
{:spec
 "stateDiagram-v2
    [*] --> Still
    Still --> [*]
    Still --> Moving
    Moving --> Still
    Moving --> Crash
    Crash --> [*]"}

^{:org.scicloj/rendering-hint :tex}
  
{:spec "G_{\\mu\\nu}\\equiv R_{\\mu\\nu} - {\\textstyle 1 \\over 2}R\\,g_{\\mu\\nu} = {8 \\pi G \\over c^4} T_{\\mu\\nu}"}

^{:org.scicloj/rendering-hint :plotly}
{:data [{:z [[1 2 3] [3 2 1]] :type "surface"}]}



(ds/->dataset {:a ["first column"]})
(type {})

;; (clerk/clear-cache!)
