(ns org.scicloj.casagemas.dataset
  (:require [nextjournal.clerk :as clerk]
            [tech.v3.dataset]
            [nextjournal.clerk.viewer :as v]))

(defn viewers []
  [
   {:pred tech.v3.dataset/dataset?
    :transform-fn (clerk/update-val #(clerk/table {:head (tech.v3.dataset/column-names %)
                                                   :rows (tech.v3.dataset/rowvecs %)}))}])
