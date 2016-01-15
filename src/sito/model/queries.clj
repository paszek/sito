(ns sito.model.queries
  (:require [sito.model.entities :as e]
            [sito.model.db]
            [korma.core :as k]
            [korma.db :as k-db]))

(defn read-expense-table-info []
  (k/exec-raw [(str "select count(*) from information_schema.tables "
                    "where table_name='expense'")]))

(defn read-expense-all []
  (k/select e/expense
            (k/with e/appuser (k/fields [:name :appuser_name]))
            (k/with e/category)
            (k/order :transaction_date)))

(defn read-expense-limit [l]
  (k/select e/expense
            (k/with e/appuser)
            (k/with e/category)
            (k/order :transaction_date)
            (k/limit l)))

(defn read-expense-id [id]
  (k/select e/expense
            (k/with e/appuser)
            (k/with e/category)
            (k/where (= :id id))))

(defn create-expense [name amount category trans-date]
  (k-db/transaction
   (let [expense-id (k/insert e/expense
                              (k/values {:name name 
                                         :amount amount
                                         :transaction_date trans-date}))]
     (k/insert "expense_category"
               (k/values {:expense_id (:id expense-id) 
                          :category_id category})))))

(defn read-category-all []
  (k/select e/category
            (k/order :id)))
