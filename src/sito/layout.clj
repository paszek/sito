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
    (h/include-css "/stylesheet/base.css")]
   [:body {:class "app"}
    [:div {:class "wrapper"}
     [:div {:class "header"}
      [:div {:class "max container"}
       [:div {:class "inline side"}] 
       [:div {:class "inline center"} [:h1 "SITO"]] 
       [:div {:class "inline side right"} 
        (if (= :true logged) 
          [:a {:href "/logout/" :class "inline power-off square-2"} " " ]
          [:div {:class "inline square-2"} " " ])]]]
     [:div {:id "content" :class "main max container"} body]]]))

