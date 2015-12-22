(ns sito.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :refer [site]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]            
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]
            [clojure.java.io :as io]
            [clojure.java.jdbc :as db]))

(defroutes app-routes
  (GET "/" [] "SITO")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app-routes) {:port port :join? false})))

;; For interactive development:
;; (.stop server)
;; (def server (-main))
