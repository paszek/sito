(ns sito.model.db
  (:use korma.db)
  (:require [clojure.string :as str]
            [environ.core :refer [env]]))

(def db-spec
  (env :korma-db))

(def db-postgres
  (postgres db-spec))

(defdb korma-db db-postgres)
