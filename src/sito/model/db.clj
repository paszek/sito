(ns sito.model.db
  (:require [environ.core :refer [env]]
            [korma.db :as k]
            [lobos.connectivity :as l]))

(defn- create-uri [url] (java.net.URI. url))

(defn- parse-username-and-password [db-uri]
  (clojure.string/split (.getUserInfo db-uri) #":"))

(defn- subname [db-uri]
  (format "//%s:%s%s" (.getHost db-uri) (.getPort db-uri) (.getPath db-uri)))

(defn korma-connection-map
  "Converts Heroku's DATABASE_URL to a map that you can pass to Korma's
  defdb fn"
  [heroku-database-url]
  (let [db-uri (create-uri heroku-database-url)
        [username password] (parse-username-and-password db-uri)]
    {
     :user username
     :password password
     :subname (subname db-uri)}))

(def db-url
  (env :database-url))

(def db-conf
;  (env :korma-db)
  (korma-connection-map db-url))

(def db-postgres
  (k/postgres db-conf))

(k/defdb korma-db db-postgres)

(l/open-global  db-postgres)
