(ns sito.layout
  (:require [hiccup.page :as h]))

(defn common [title & body]
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
     [:div {:class "header"} [:h1 "SITO"]]
     [:div {:id "content" :class "main container"} body]]]))
