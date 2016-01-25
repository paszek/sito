(ns sito.migration 
  (:require [sito.model.db]
            [sito.model.queries :as q]
            [lobos.core :as l]))

(def categories
  ["restaurant" 
   "hotel"
   "grocery"
   "alcohol"
   "car maintenance"
   "car fuel"
   "bank charges"
   "tax"
   "phone"
   "transportation"
   "medical"
   "gift"
   "clothes"
   "books"
   "education"
   "events"
   "insurance"
   "household energy"
   "household maintenance"
   "entertainment"
   "other"])

(defn populate-category [categories]
  (pmap #(when (empty? (q/read-category-name %)) 
          (q/create-category %)) categories))

(defn migrate-sito []
  (println "Migration...")
  (l/migrate) ;needs  lobos/migrations.clj
  (populate-category categories)
  (println "Done!"))


