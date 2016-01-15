(ns sito.view
  (:require [hiccup.page :as h]
            [hiccup.form :as form]
            [ring.util.anti-forgery :as anti-forgery]
            [sito.layout :as layout]))

(defn expense-form [categories]
  [:div {:id "expense-form" :class "form-container"}
   (form/form-to [:post "/expenses/"]
                 (anti-forgery/anti-forgery-field)
                 [:section
                  (form/label "name" "What?")
                  (form/text-field "name")
                  (form/label "amount" "How much?")
                  (form/text-field "amount")
                  (form/label "trans-date" "When?")
                  [:input {:type "date" :name "trans-date"}]
                  (form/label "category" "Category")
                  [:select {:name "category"} 
                   (form/select-options categories "1")]]
                 (form/submit-button "ADD"))])

(defn expenses-list [expenses]
  [:table 
   [:thead [:tr [:th "Name"] [:th "Amount"] [:th "Category"] [:th "Date"] [:th "User"]]]
   [:tbody
    (map (fn [expense]
           [:tr {:id (str "id-" (:id expense))} 
            [:td [:a {:href (str "/expenses/" (:id expense))} (:name expense)]]
            [:td (:amount expense)]
            [:td (:name (first (:category expense)))]
            [:td (:transaction_date expense)]
            [:td (:appuser_name expense)]])
         expenses)]])

(defn categories-as-name-id [categories]
  (map (juxt :name :id) categories))

;/
(defn index [body]
  (layout/common "SITO" body))

;/expenses/
(defn expenses [expenses categories]
  (layout/common "SITO expenses"
                 (expense-form (categories-as-name-id categories))
                 (expenses-list expenses)))

;/expenses/:id
(defn expense [expense]
  (layout/common (str "SITO expense " (:name expense))
                 [:div {:class "container"} 
                  [:div (:name expense)]]))
