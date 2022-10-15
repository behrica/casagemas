(ns user
  (:require [nextjournal.clerk :as clerk]
            ;; [tech.v3.dataset :as ds]
            [nextjournal.clerk.tap]
            [org.scicloj.casagemas]
            [org.scicloj.casagemas.dataset]
            [org.scicloj.casagemas.kroki]
            [nextjournal.clerk.viewer :as v]))
(comment
  (clerk/clear-cache!)
  (clerk/serve! {:browse? true})

  (nextjournal.clerk.viewer/reset-viewers!
   :default
   (-> nextjournal.clerk.viewer/default-viewers
       (nextjournal.clerk.viewer/add-viewers [nextjournal.clerk.tap/tap-viewer])
       (nextjournal.clerk.viewer/add-viewers
        (concat (org.scicloj.casagemas/viewers)
                (org.scicloj.casagemas.dataset/viewers)
                (org.scicloj.casagemas.kroki/viewers)))))






  ;; (nextjournal.clerk/show! 'nextjournal.clerk.tap)

  ;; (add-custom-tap-viewers)



  :ok)
