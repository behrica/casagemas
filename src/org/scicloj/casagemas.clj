(ns org.scicloj.casagemas
  (:require [nextjournal.clerk :as clerk]
           [nextjournal.clerk.viewer :as v]))

(defn viewers []
  [

   ;; vega lite viewer
   {:pred (fn [v] (= :vega-lite (:org.scicloj/rendering-hint (meta v))))
    :render-fn (quote v/vega-lite-viewer)
    :transform-fn (fn [wrapped-value]
                    (def wrapped-value wrapped-value)
                    (-> wrapped-value
                        v/mark-presented
                        (merge (meta wrapped-value))))}

   ;; plotly
   {:pred (fn [v] (= :plotly (:org.scicloj/rendering-hint (meta v))))
    :render-fn '(fn [value] (v/plotly-viewer value))
    :transform-fn v/mark-presented}



   ;; tex
   {:pred (fn [v] (= :tex (:org.scicloj/rendering-hint (meta v))))
    :render-fn '(fn [value] (v/katex-viewer (:spec value)))
    :transform-fn v/mark-presented}


   ;; mermaid viwer
   {:pred (fn [v] (= :mermaid (:org.scicloj/rendering-hint (meta v))))
    :transform-fn v/mark-presented
    :render-fn '(fn [value]
                  (v/html
                   (when value
                     [v/with-d3-require {:package ["mermaid@8.14/dist/mermaid.js"]}
                      (fn [mermaid]
                        [:div {:ref (fn [el] (when el
                                              (.render mermaid (str (gensym)) (:spec value) #(set! (.-innerHTML el) %))))}])])))}




   {:pred (fn [v] (= :cytoscape (:org.scicloj/rendering-hint (meta v))))
    :transform-fn nextjournal.clerk/mark-presented
    :render-fn '(fn [value]
                  (v/html (when value [v/with-d3-require {:package ["cytoscape@3.21.0"]}
                                       (fn [cytoscape]
                                         [:div {:style {:height "500px"}
                                                 :ref (fn [el]
                                                        (when el
                                                          (-> value (assoc :container el) clj->js cytoscape)))}])])))}])
