(ns lobos.migrations
  (:refer-clojure :exclude [alter drop bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema)))

(defmigration add-appuser-table
  (up [] (create
          (table :appuser
                 (integer :id :auto-inc :primary-key)
                 (text :name :unique))))
  (down [] (drop (table :appuser))))

(defmigration add-expense-table
  (up [] (create
          (table :expense
                 (integer :id :auto-inc :primary-key)
                 (text :name)
                 (numeric :amount 11 2)
                 (timestamp :transaction_date)
                 (integer :appuser_id [:refer :appuser :id :on-delete :set-null]))))
  (down [] (drop (table :expense))))

(defmigration add-category-table
  (up [] (create
          (table :category
                 (integer :id :auto-inc :primary-key)
                 (text :name :unique))))
  (down [] (drop (table :category))))

(defmigration add-expense-category-table
  (up [] (create
          (table :expense_category
                 (integer :category_id [:refer :category :id :on-delete :set-null])
                 (integer :expense_id [:refer :expense :id :on-delete :set-null]))))
  (down [] (drop (table :expense_category))))

(defmigration add-password-column
  (up [] (alter :add (table :appuser (varchar :hash_password 255))))
  (down [] ))
