(ns ^:figwheel-hooks guis.core
  (:require
   [goog.dom :as gdom]
   [reagent.dom :as rdom]
   [guis.tasks.counter :as counter]
   [guis.tasks.temperature-converter :as temperature-converter]))

(defn multiply [a b] (* a b))

(defn get-app-element []
  (gdom/getElement "app"))

(defn app []
  [:div
   [:h2 "Counter"]
   [counter/counter]
   [:h2 "Temperature converter"]
   [temperature-converter/converter]])

(defn mount [el]
  (rdom/render [app] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element))
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  
:after-load
