(ns sito.migration
  (:require [clojure.java.jdbc :as jdbc]
            [sito.model :as model]))

(defn migrate []
  (println "Init"))

(defn migrated? []
  (-> (jdbc/query model/database-url
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='expense'")])
      first :count pos?))

(defn migrate-copy []
  (when (not (migrated?))
    (print "Creating database structure...") (flush)
    (jdbc/db-do-commands model/database-url
                        (jdbc/create-table-ddl
                         :expense
                         [:id :serial "PRIMARY KEY"]
                         [:name :text "NOT NULL"]
                         [:transaction_date :timestamp
                          "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
    (println " done")))
