(ns guis.tasks.counter
  "The task is to build a frame containing a label or read-only textfield T
   and a button B. Initially, the value in T is “0” and each click of B
   increases the value in T by one."
  (:require
   [reagent.core :as r]))

(defn counter []
  (let [state (r/atom 2)]
    (fn []
      [:div
       [:input {:disabled true :value @state}]
       [:button {:on-click #(swap! state inc)} "Count"]])))
