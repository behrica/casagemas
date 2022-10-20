(ns user
  (:require [nextjournal.clerk :as clerk]
            [nextjournal.clerk.tap]
            [org.scicloj.casagemas]
            [org.scicloj.casagemas.dataset]
            [org.scicloj.casagemas.kroki]
            [nextjournal.clerk.viewer :as v]))

(comment
  (clerk/serve! {:browse? true})
  (clerk/show! 'nextjournal.clerk.tap)

  (clerk/clear-cache!)


  (nextjournal.clerk.viewer/reset-viewers!
   ;; :default
   (find-ns 'nextjournal.clerk.tap)
   (-> nextjournal.clerk.viewer/default-viewers
       ;; (nextjournal.clerk.viewer/add-viewers [nextjournal.clerk.tap/tap-viewer])
       (nextjournal.clerk.viewer/add-viewers
        (concat (org.scicloj.casagemas/viewers)
                (org.scicloj.casagemas.dataset/viewers)
                (org.scicloj.casagemas.kroki/viewers))))))
