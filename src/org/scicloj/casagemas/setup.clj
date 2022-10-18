(ns org.scicloj.casagemas.setup

  (:require [nextjournal.clerk :as clerk]
            [nextjournal.clerk.tap]
            [org.scicloj.casagemas]
            [org.scicloj.casagemas.dataset]
            [org.scicloj.casagemas.kroki]
            [nextjournal.clerk.viewer :as v]))


(defn start-clerk! []
  (println "Start Clerk")
  (future (clerk/serve! {:browse? true}))
  (Thread/sleep 10000)
  (println "Show tap inspecor")
  (clerk/show! 'nextjournal.clerk.tap)

  (println "Configure Clerk viewers")
  (nextjournal.clerk.viewer/reset-viewers!
   ;; :default
   (find-ns 'nextjournal.clerk.tap)
   (-> nextjournal.clerk.viewer/default-viewers
       (nextjournal.clerk.viewer/add-viewers [nextjournal.clerk.tap/tap-viewer])
       (nextjournal.clerk.viewer/add-viewers
        (concat (org.scicloj.casagemas/viewers)
                (org.scicloj.casagemas.dataset/viewers)
                (org.scicloj.casagemas.kroki/viewers))))))
 
