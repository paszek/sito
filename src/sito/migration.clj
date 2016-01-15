(ns sito.migration 
  (:require [sito.model.db]
            [lobos.core :as l]))

(defn migrate-sito []
  (println "Migration...")
  (l/migrate) ;needs  lobos/migrations.clj
  (println " Done!"))
