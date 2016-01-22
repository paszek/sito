(ns sito.handler
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]
            [sito.migration :refer [migrate-sito]]
            [sito.auth :refer [wrap-auth]]
            [sito.controller :refer [app-routes]]))

(def app
  (-> app-routes
      (wrap-auth)
      (wrap-defaults site-defaults)))

(defn -main [& [port]]
  (migrate-sito)
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty app {:port port :join? false})))
