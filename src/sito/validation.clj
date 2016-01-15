(ns sito.validation
  (:require [clojure.string :as str]))

(def amount-regex
  #"[-+]?[0-9]+\.?[0-9]+")

(def timestamp-regex
  #"[0-9]{4}[-]?[0-9]{1,2}[-]?[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}[.]?[0-9]{1,6}")

(defn exp-name [input]
  input)

(defn exp-amount [input]
  (let [float-input (re-matches amount-regex (str/replace (str "0" input) #"," "."))]
    (if (some? float-input)
      (* 1.0 (read-string float-input))
      0.0)))

(defn exp-category [input]
  (Integer/parseInt input))

(defn exp-trans-date [input]
  (let [timestamp-input (re-matches timestamp-regex (str input " 12:00:00"))]
    (if (some? timestamp-input)
      (java.sql.Timestamp/valueOf timestamp-input)
      nil)))
