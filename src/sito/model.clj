(ns sito.model
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.string :as str]
            [environ.core :refer [env]]
            [sito.model.queries :as q]))

(def database-url
  (env :database-url))

(defn under-to-keyword-format [s] 
  (str/replace s \_ \-))

(defn db-query [q]
  (jdbc/query database-url q 
              :identifiers under-to-keyword-format))

;;expense
(defn expense-all []
  (q/read-expense-all))

(defn expense-limit [range]
  (into [] (db-query [(str "select * from expense order by id desc limit " range)])))

(defn expense [id]
  (first (db-query [(str "select * from expense where id = " id)])))

(defn expense-create [name amount category trans-date]
  (jdbc/with-db-transaction [trans-conn database-url] 
    (let [expense-id (first (jdbc/insert! trans-conn 
                                          :expense 
                                          {:name name 
                                           :amount amount
                                           :transaction_date trans-date}))]
      (jdbc/insert! trans-conn
                      :expense_category 
                      {:expense_id (:id expense-id) 
                       :category_id category}))))


;;category
(defn category-all []
  (into [] (db-query ["select * from category order by id desc"])))


