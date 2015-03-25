(ns cljx-start-lib.routes
  #+clj
  (:require
    [compojure.core               :refer [defroutes GET]] 
    [compojure.handler            :as handler]
    [compojure.route              :as route]
    [ring.middleware.file         :as file]
    [ring.middleware.file-info    :as file-info]
    [ring.middleware.json         :as json]
    [ring.middleware.resource     :as resource]
    [ring.middleware.content-type :as content-type]
    [ring.util.response           :as response]
    [hiccup.page                  :refer [html5 
                                          include-js 
                                          include-css]]))

#+clj
(defn wrap-nocache 
  "disable client cache, helps with source map issues."
  [handler]
  (fn [request]
    (let [response (handler request)]
    (-> response
      (assoc-in [:headers "Cache-Control"] "no-cache, no-store, must-revalidate")
      (assoc-in [:headers "Pragma"] "no-cache")
      (assoc-in [:headers "Expires"] "0")))))

#+clj
(defn render
  "render a hiccup form"
  [& forms]
  (html5 
     [:body
      forms 
      (include-js 
        "/js/goog/base.js" 
        "/js/{{sanitized}}.js")
      [:script "goog.require('cljx_start_lib.main');"]]))

#+clj
(defn index [content]
  (render [:div#main content]))

#+clj
(defroutes server
  
  (GET "/" [] 
    (index nil))
  
  (GET "/example/json" [params]
    {:body (range 1 10)})
  
  (route/resources "/")
  
  (route/not-found (index "File-not-found")))

#+clj
(def app
  (let [wrapper (-> (handler/site server)
                  (resource/wrap-resource "/META-INF/resources")
                  (resource/wrap-resource "/public")
                  (content-type/wrap-content-type)
                  json/wrap-json-body
                  json/wrap-json-params
                  json/wrap-json-response)]
    (wrap-nocache wrapper)))
