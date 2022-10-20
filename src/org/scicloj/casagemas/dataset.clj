(ns org.scicloj.casagemas.dataset
  (:require [nextjournal.clerk :as clerk]
            [tech.v3.dataset]
            [nextjournal.clerk.viewer :as v]))

(def viewer-description
  {:tech.ml/dataset
   {:pred-via-pred tech.v3.dataset/dataset?
    :pred-via-rendering-hint (fn [v] (= :tech.ml.dataset
                                       (:org.scicloj/rendering-hint (meta v))))
    :transform-fn (clerk/update-val #(clerk/table {:head (tech.v3.dataset/column-names %)
                                                   :rows (tech.v3.dataset/rowvecs %)}))}})
