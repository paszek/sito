(ns sito.view
  (:require [hiccup.form :as form]
            [clj-time.format :as f]
            [ring.util.anti-forgery :as anti-forgery]
            [sito.layout :as layout]))

(def date-formatter (f/formatter "yyyy-MM-dd"))

(defn expense-form [categories]
  [:div {:id "expense-form" :class "form-container"}
   (form/form-to [:post "/expenses/"]
                 (anti-forgery/anti-forgery-field)
                 [:section
                  [:div {:class "labeled-input center container"}
                   [:label {:for "name" :class "inline width-6 right"} "What? "]
                   [:input {:id "name" :type "text" :class "inline width-10" :name "name"}]]
                  [:div {:class "labeled-input center container"}
                   [:label {:for "price" :class "inline width-6 right"} "How much? "]
                   [:input {:id "price" :type "text" :class "inline width-10" :name "price"}]]
                  [:div {:class "labeled-input center container"}
                   [:label {:for "trans-date" :class "inline width-6 right"} "When? "]
                   [:input {:id "trans-date" :type "date" :class "inline width-10" :name "trans-date"}]]
                  [:div {:class "labeled-input center container"}
                   [:label {:for "category" :class "inline width-6 right"} "Category "]                   
                   [:select {:id "category" :name "category" :class "inline width-10"} 
                    (form/select-options categories (second (first categories)))]]]
                 [:div {:class "center container marg-vert-1"} 
                  [:input {:type "submit" :value "+" :class "square-2"}]])])

(defn expenses-list [expenses]
  [:table 
   [:thead [:tr [:th "Name"] [:th "Price"] [:th "Category"] [:th "Date"] [:th "User"]]]
   [:tbody
    (map (fn [expense]
           [:tr {:id (str "id-" (:id expense))} 
            [:td [:a {:href (str "/expenses/" (:id expense))} (:name expense)]]
            [:td (:amount expense)]
            [:td (:name (first (:category expense)))]
            [:td (f/unparse date-formatter (:transaction_date expense))]
            [:td (:appuser_name expense)]])
         expenses)]])

(defn login-form []
  [:div {:id "login-form" :class "login"}
   (form/form-to [:post "/login/"]
                 (anti-forgery/anti-forgery-field)
                 [:section
                  [:div {:class "labeled-input center container"}
                   (form/text-field "username")]
                  [:div {:class "labeled-input center container"}
                   (form/password-field "password")]]
                 [:div {:class "center container marg-vert-1"} 
                  [:input {:type "submit" :value " " :class "square-2"}]])])

(defn categories-as-name-id [categories]
  (map (juxt :name :id) categories))

;/login
(defn login []
  (layout/common :false "SITO login" 
                 (login-form)))

;/expenses/
(defn expenses [expenses categories]
  (layout/common :true "SITO expenses"
                 (expense-form (categories-as-name-id categories))
                 (expenses-list expenses)))

;/expenses/:id
(defn expense [expense]
  (layout/common :true (str "SITO expense " (:name expense))
                 [:div {:class "container"} 
                  [:div (:name expense)]]))

;404
(defn not-found []
  (layout/common :false "SITO 404"
                 [:div {:class "container center big"} "404"]))
