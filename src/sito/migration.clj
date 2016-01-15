(ns sito.migration 
  (:require [sito.model.db :as conf]
            [lobos.core :as l]
            [lobos.connectivity :as l-c]))

(l-c/open-global conf/db-conf)

(defn migrate-sito []
  (println "Migration...")
  (l/migrate) ;needs  lobos/migrations.clj
  (println " Done!"))
