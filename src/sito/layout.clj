(ns sito.layout
  (:require [hiccup.page :as h]))

(defn common [logged title & body]
  (h/html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
    [:meta {:name "viewport" :content
            "width=device-width, initial-scale=1, maximum-scale=1"}]
    [:title title]
    (h/include-css "/stylesheet/base.css")
    (h/include-js "/script/moment.min.js" "/script/base.js")]
   [:body {:class "app" :onload "onLoad()"}
    [:div {:class "wrapper"}
     [:div {:class "header"}
      [:div {:class "max container"}
       [:div {:class "inline side"}] 
       [:div {:class "inline center vert-middle"} [:a {:href "/" :class "inline vert-middle"} [:h1 "SITO"]]] 
       [:div {:class "inline side right"} 
        (if (= :true logged) 
          [:a {:href "/logout/" :class "inline power-off square-2"} " " ]
          [:a {:href "/login/" :class "inline power-on square-2"} " " ])]]]
     [:div {:id "content" :class "main max container"} body]]]))

