(ns org.scicloj.casagemas
  (:require [nextjournal.clerk :as clerk]
           [nextjournal.clerk.viewer :as v]))

(defn viewers []
  [

   ;; vega lite viewer
   {:pred (fn [v] (= :vega.github.io/vega-lite
                    (:org.scicloj/rendering-hint (meta v))))
    :transform-fn v/vl}

   ;; plotly
   {:pred (fn [v] (= :github.com/plotly
                    (:org.scicloj/rendering-hint (meta v))))
    :transform-fn (fn [val] (v/plotly val))}



   {:pred (fn [v] (= :latex-project.org/latex
                    (:org.scicloj/rendering-hint (meta v))))
    :transform-fn (comp v/tex (clerk/update-val :spec))}



   ;;  markdown
   {:pred (fn [v] (= :nextjournal/markdown
                    (:org.scicloj/rendering-hint (meta v))))
    :transform-fn (comp v/md (clerk/update-val :spec))}

   ;;  table
   {:pred (fn [v] (= :clojure/table
                    (:org.scicloj/rendering-hint (meta v))))
    :transform-fn v/table}

   ;;  code
   {:pred (fn [v] (= :code
                    (:org.scicloj/rendering-hint (meta v))))
    :transform-fn (comp v/code (clerk/update-val :spec))}

   ;;  html
   {:pred (fn [v] (= :html
                    (:org.scicloj/rendering-hint (meta v))))
    :transform-fn v/html}


   ;; mermaid viewer
   {:pred (fn [v] (= :mermaid-js.github.io/mermaid
                    (:org.scicloj/rendering-hint (meta v))))
    :transform-fn v/mark-presented
    :render-fn '(fn [value]
                  (v/html
                   (when value
                     [v/with-d3-require {:package ["mermaid@8.14/dist/mermaid.js"]}
                      (fn [mermaid]
                        [:div {:ref (fn [el] (when el
                                              (.render mermaid (str (gensym)) (:spec value) #(set! (.-innerHTML el) %))))}])])))}




   {:pred (fn [v] (= :cytoscape.org/cytoscape
                    (:org.scicloj/rendering-hint (meta v))))
    :transform-fn nextjournal.clerk/mark-presented
    :render-fn '(fn [value]
                  (v/html (when value [v/with-d3-require {:package ["cytoscape@3.21.0"]}
                                       (fn [cytoscape]
                                         [:div {:style {:height "500px"}
                                                :ref (fn [el]
                                                       (when el
                                                         (-> value (assoc :container el) clj->js cytoscape)))}])])))}])


(->> (viewers) (map :pred))
