(ns cljx-start-lib.view
  #+cljs
  (:require 
    [{{name}}.core :as core])
  #+cljs 
  (:require-macros
    [cljx-start-lib.view :refer [log
                                 get-element-by-id
                                 innerhtml
                                 innerhtml-by-id]]))

(defmacro log [& forms]
  `(js/console.log "[{{name}}]" (js/Date.) "> " ~@forms))

(defmacro get-element-by-id [id]
  `(js/document.getElementById ~id))

(defmacro innerhtml [el html]
  `(set! (.-innerHTML ~el) ~html))

(defmacro innerhtml-by-id [id html]
  `(innerhtml (get-element-by-id id) html))

#+cljs
(defn main []
  (log (core/foo)))