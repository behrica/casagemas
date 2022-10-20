(ns org.scicloj.casagemas
  (:require [nextjournal.clerk :as clerk]
           [nextjournal.clerk.viewer :as v]))


(def viewer-descriptions
  {
   :cytoscape.org/cytoscape
   {:pred-via-rendering-hint (fn [v] (= :cytoscape.org/cytoscape
                                       (:org.scicloj/rendering-hint (meta v))))
    :transform-fn nextjournal.clerk/mark-presented
    :render-fn '(fn [value]
                  (v/html (when value [v/with-d3-require {:package ["cytoscape@3.21.0"]}
                                       (fn [cytoscape]
                                         [:div {:style {:height "500px"}
                                                :ref (fn [el]
                                                       (when el
                                                         (-> value (assoc :container el) clj->js cytoscape)))}])])))}
   :mermaid-js.github.io/mermaid
   {:pred-via-rendering-hint (fn [v] (= :mermaid-js.github.io/mermaid
                                       (:org.scicloj/rendering-hint (meta v))))
    :transform-fn v/mark-presented
    :render-fn '(fn [value]
                  (v/html
                   (when value
                     [v/with-d3-require {:package ["mermaid@8.14/dist/mermaid.js"]}
                      (fn [mermaid]
                        [:div {:ref (fn [el] (when el
                                              (.render mermaid (str (gensym)) (:spec value) #(set! (.-innerHTML el) %))))}])])))}

   ;; vega lite viewer
   :vega.github.io/vega-lite
   {:pred-via-rendering-hint (fn [v] (= :vega.github.io/vega-lite
                                       (:org.scicloj/rendering-hint (meta v))))
    :transform-fn v/vl}


   ;; plotly
   :github.com/plotly
   {:pred-via-rendering-hint (fn [v] (= :github.com/plotly
                                       (:org.scicloj/rendering-hint (meta v))))
    :transform-fn (fn [val] (v/plotly val))}


   :latex-project.org/latex
   {:pred-via-rendering-hint (fn [v] (= :latex-project.org/latex
                                       (:org.scicloj/rendering-hint (meta v))))
    :transform-fn (comp v/tex (clerk/update-val :spec))}



   ;;  markdown
   :nextjournal/markdown
   {:pred-via-rendering-hint (fn [v] (= :nextjournal/markdown
                                       (:org.scicloj/rendering-hint (meta v))))
    :transform-fn (comp v/md (clerk/update-val :spec))}

   ;;  table

   :clojure/table
   {:pred-via-rendering-hint (fn [v] (= :clojure/table
                                       (:org.scicloj/rendering-hint (meta v))))
    :transform-fn v/table}

   ;;  code
   :code
   {:pred-via-rendering-hint (fn [v] (= :code
                                       (:org.scicloj/rendering-hint (meta v))))
    :transform-fn (comp v/code (clerk/update-val :spec))}

   ;;  html
   :html
   {:pred-via-rendering-hint (fn [v] (= :html
                                       (:org.scicloj/rendering-hint (meta v))))
    :transform-fn v/html}})




(defn get-pure-viewers
  "Returns map of viewer-id -> {:render-fn   :transform-fn}]"
  [viewer-descriptions]
  (into {}
        (map (fn [[k viewer-description]]
               (hash-map k
                         (select-keys
                          viewer-description
                          [:transform-fn :render-fn])))
             viewer-descriptions)))



(comment
  (get-pure-viewers viewer-descriptions))
