(defproject sito "0.1.0"
  :description "Household expediture management"
  :url "https://github.com/paszek/sito"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [environ "1.0.0"]                 
                 [org.clojure/java.jdbc "0.3.7"]
                 [org.postgresql/postgresql "9.4-1201-jdbc4"]
                 [korma "0.4.2"]
                 [lobos "1.0.0-beta3"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.9.7"]
            [environ/environ.lein "0.3.1"]]
  :ring {:handler sito.handler/app
         :init sito.migration/migrate-sito}
  :hooks [environ.leiningen.hooks]
  :uberjar-name "sito-standalone.jar")
