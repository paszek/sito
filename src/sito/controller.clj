(ns sito.controller
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as res]
            [sito.model :as model]
            [sito.view :as view]
            [sito.validation :as val]
            [sito.auth :as auth]))

(defn lookup-appuser [username password]
  (if-let [appuser (model/appuser username)]
    (if (auth/check-password password (:hash_password appuser))
      (dissoc appuser :hash_password))))

(defn get-expenses-id [{params :params :as req}]
  (auth/auth? req)
  (view/expense (model/expense (val/id (:id params)))))

(defn get-expenses [req]
  (auth/auth? req)
  (view/expenses (model/expense-all) (model/category-all)))

(defn post-expenses [{params :params appuser :identity :as req}]
  (auth/auth? req)
  (model/expense-create 
   (val/exp-name (:name params)) 
   (val/exp-price (:price params)) 
   (val/id (:category params)) 
   (val/exp-trans-date (:trans-date params))
   (:id appuser))
  (res/redirect "/"))

(defn get-login [req]
  (view/login))

(defn post-login [{params :params session :session :as req}]
  (let [username (:username params)
        password (:password params)]
    (if-let [appuser (lookup-appuser username password)]
      (-> (res/redirect "/")
          (assoc :session (assoc session :identity appuser)))
      (res/redirect "/login/"))))

(defn post-logout [{params :params :as req}]
  (-> (res/redirect "/login/")
      (assoc :session {})))

(defn get-not-found [req]
  (view/not-found))

(defroutes app-routes
  (GET "/expenses/:id{[0-9]+}" [] get-expenses-id)
  (GET "/expenses/" [] get-expenses)
  (POST "/expenses/" [] post-expenses)
  (GET "/login/" [] get-login)
  (POST "/login/" [] post-login)
  (GET "/logout/" [] post-logout)
  (GET "/" [] get-expenses)

  (route/not-found get-not-found))
