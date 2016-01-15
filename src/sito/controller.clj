(ns sito.controller
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as res]
            [sito.model :as model]
            [sito.view :as view]
            [sito.validation :as val]))

(defroutes app-routes
  (GET "/expenses/:id{[0-9]+}" [id] 
       (view/expense (model/expense id)))
  (GET "/expenses" [] 
       (view/expenses (model/expense-all) (model/category-all)))
  (POST "/expenses/" [name amount category trans-date :as req]
        (model/expense-create 
         (val/exp-name name) 
         (val/exp-amount amount) 
         (val/exp-category category) 
         (val/exp-trans-date trans-date))
        (res/redirect "/expenses")) ;;todo redirect
  (GET "/" [] 
       (view/expenses (model/expense-limit 5) (model/category-all)))

  (route/not-found 
   (view/index "404")))
