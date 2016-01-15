(ns sito.handler
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]
            [sito.migration :refer [migrate-sito]]
            [sito.controller :refer [app-routes]]))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main [& [port]]
  (migrate-sito)
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty app {:port port :join? false})))
