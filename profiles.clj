{:dev 
 {:env 
  {:database-url "postgresql://pl:pass@localhost:5432/sito"
   :production false}
  :dependencies [[javax.servlet/servlet-api "2.5"]
                 [ring/ring-mock "0.3.0"]]}
 :production 
 {:env 
  {:production true}}
 :uberjar 
 {:aot :all}}
