(ns sito.model.queries
  (:require [sito.model.entities :as e]
            [sito.model.db]
            [korma.core :as k]
            [korma.db :as k-db]))

(defn read-expense-table-info []
  (k/exec-raw [(str "select count(*) from information_schema.tables "
                    "where table_name='expense'")]))

(def expense-base (-> (k/select* e/expense)
                      (k/with e/appuser (k/fields [:name :appuser_name]))
                      (k/with e/category)
                      (k/order :transaction_date :DESC)))

(defn read-expense-all []
  (-> expense-base
      (k/select)))

(defn read-expense-limit [l]
  (-> expense-base
      (k/limit l)
      (k/select)))

(defn read-expense-id [id]
  (-> expense-base
      (k/where (= :id id))
      (k/select)))

(defn create-expense [name amount category trans-date appuser-id]
  (k-db/transaction
   (let [expense-id (k/insert e/expense
                              (k/values {:name name 
                                         :amount amount
                                         :transaction_date trans-date
                                         :appuser_id appuser-id}))]
     (k/insert "expense_category"
               (k/values {:expense_id (:id expense-id) 
                          :category_id category})))))

(defn read-category-all []
  (k/select e/category (k/order :id)))

(defn read-category-name [name]
  (k/select e/category (k/where (= :name name))))

(defn create-category [name]
  (k/insert e/category (k/values {:name name})))

(defn read-appuser [username]
  (k/select e/appuser (k/where (= :name username))))

(defn create-appuser [username password]
  (k/insert e/appuser (k/values {:name username :hash_password password})))
