(ns sito.model.db
  (:use korma.db)
  (:require [environ.core :refer [env]]))

(def db-url
  (env :database-url))

(def db-conf
  (env :korma-db))

(def db-postgres
  (postgres db-conf))

(defdb korma-db db-postgres)
