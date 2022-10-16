(ns org.scicloj.casagemas.kroki
  (:require
             [clj-http.client :as client]
             [nextjournal.clerk :as clerk]
             [nextjournal.clerk.viewer :as v]))

(defn kroki [s type format]
  (client/post "https://kroki.io/" {:content-type :json
                                    ;; :as :byte-array
                                    :form-params
                                    {:diagram_source s
                                     :diagram_type (name type) :output_format (name format)}}))

(defn viewers []
  [
   {:pred (fn [v]
            (= :kroki.io/kroki
               (:org.scicloj/rendering-hint (meta v))))
    :render-fn (quote v/html)
    :transform-fn (fn [wrapped-value]
                    (def wrapped-value wrapped-value)
                    (let [kroki-result
                          (kroki (:spec (:nextjournal/value wrapped-value))
                                 (-> wrapped-value :nextjournal/value meta :diagram-type)
                                 :svg)]
                      (-> (assoc  wrapped-value
                                  :nextjournal/value (:body kroki-result))
                          v/mark-presented)))}])
