(ns sito.model.entities
  (:use korma.core)
  (:require [sito.model.db]
            [clj-time.coerce :as c]))

(declare appuser expense category)

(defentity appuser
  (has-many expense))

(defentity expense
  (belongs-to appuser)
  (transform (fn [{transaction_date :transaction_date :as e}]
               (if transaction_date
                 (assoc e :transaction_date (c/from-sql-time transaction_date)) 
                 e)))
  (many-to-many category :expense_category))

(defentity category
  (many-to-many expense :expense_category))

