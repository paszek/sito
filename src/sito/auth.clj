(ns sito.auth
  (:require [environ.core :refer [env]]
            [clj-time.core :as time]
            [ring.util.response :as res]
            [buddy.hashers :as hashers]
            [buddy.sign.jws :as jws]
            [buddy.auth :refer [authenticated? throw-unauthorized]]
            [buddy.auth.backends.session :refer [session-backend]]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]))

(def sessions (atom {}))

(def pwd-alg #{:bcrypt+sha512})
(def jws-alg :hs512)

(def token-persistence 3600)

(defn unauthorized-handler [request metadata]  
  (res/redirect "/login/"))

(def auth-backend (session-backend {:unauthorized-handler unauthorized-handler}))

(defn auth? [req]
  (authenticated? req))
 
(defn wrap-auth [handler]
  (-> handler
      (wrap-authentication auth-backend)
      (wrap-authorization auth-backend)))

(defn check-password [incoming encrypted]
  (hashers/check incoming encrypted {:limit pwd-alg}))

(defn encrypt-password [pwd]
  (hashers/encrypt pwd {:alg (first pwd-alg)}))

(defn create-session [{username :name id :id :as appuser}]
  {:identity {(keyword id) (keyword username)}})
