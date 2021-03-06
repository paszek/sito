(ns sito.model
  (:require [clojure.string :as str]
            [sito.model.queries :as q]))

(defn under-to-keyword-format [s] 
  (str/replace s \_ \-))

;;expense
(defn expense-all []
  (q/read-expense-all))

(defn expense-limit [limit]
  (q/read-expense-limit limit))

(defn expense [id]
  (first (q/read-expense-id id)))

(defn expense-create [name amount category trans-date appuser-id]
  (q/create-expense name amount category trans-date appuser-id))


;;category
(defn category-all []
  (q/read-category-all))

;;appuser
(defn appuser [username]
  (first (q/read-appuser (str/lower-case username))))
