(defproject sito "0.1.0"
  :description "Household expediture management"
  :url "https://github.com/paszek/sito"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [environ "1.0.0"]                 
                 [org.clojure/java.jdbc "0.4.2"]
                 [org.postgresql/postgresql "9.4-1201-jdbc4"]]
  :plugins [[lein-ring "0.9.7"]
            [environ/environ.lein "0.3.1"]]
  :ring {:handler sito.handler/app}
  :hooks [environ.leiningen.hooks]
  :uberjar-name "sito-standalone.jar"
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}
   :production {:env {:production true}}})
