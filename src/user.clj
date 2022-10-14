(ns user
  (:require [nextjournal.clerk :as clerk]
            [tech.v3.dataset :as ds]
            ;; [nextjournal.clerk.tap]
            [org.scicloj.casagemas]
            [nextjournal.clerk.viewer :as v]))
(comment

  (clerk/serve! {:browse? true})

  (nextjournal.clerk.viewer/reset-viewers!
   (find-ns 'test-clerk-tap)
   (-> (nextjournal.clerk.viewer/get-default-viewers)
       ;; (nextjournal.clerk.viewer/add-viewers [nextjournal.clerk.tap/tap-viewer])
       (nextjournal.clerk.viewer/add-viewers (org.scicloj.casagemas/clerk-viewers))))






  ;; (nextjournal.clerk/show! 'nextjournal.clerk.tap)

  ;; (add-custom-tap-viewers)



  :ok)
