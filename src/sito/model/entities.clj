(ns sito.model.entities
  (:use korma.core)
  (:require [sito.model.db]))

(declare appuser expense category)

(defentity appuser
  (has-many expense))

(defentity expense
  (belongs-to appuser)
  (many-to-many category :expense_category))

(defentity category
  (many-to-many expense :expense_category))

