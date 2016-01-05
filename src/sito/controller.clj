(ns sito.controller
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [sito.model :as model]
            [sito.view :as view]
            [sito.validation :as validation]))

(defroutes app-routes
  (GET "/expenses/:id{[0-9]+}" [id] 
       (view/expense (model/expense id)))
  (GET "/expenses" [] 
       (view/expenses (model/expense-all) (model/category-all)))
  (POST "/expenses/" [name amount category trans-date :as req]
        (model/expense-create 
         (validation/name name) 
         (validation/amount amount) 
         (validation/category category) 
         (validation/trans-date trans-date))
        (view/expenses (model/expense-all) (model/category-all)))
  (GET "/" [] 
       (view/expenses (model/expense-take 5) (model/category-all)))

  (route/not-found 
   (view/index "404")))
