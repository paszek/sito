(ns sito.handler
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]
            [clojure.java.io :as io]
            [sito.controller :as contr]))

(def app
  (wrap-defaults contr/app-routes site-defaults))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty app {:port port :join? false})))
