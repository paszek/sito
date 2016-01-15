(ns sito.migration 
  (:require [sito.model.db :refer :all]
            [lobos.core :as l]
            [lobos.connectivity :refer :all]))

(open-global db-spec)

(defn migrate-sito []
  (println "Migration...")
  (l/migrate) ;needs  lobos/migrations.clj
  (println " Done!"))
