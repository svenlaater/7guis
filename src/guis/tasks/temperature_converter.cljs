(ns guis.tasks.temperature-converter
  "The task is to build a frame containing two textfields TC and TF
   representing the temperature in Celsius and Fahrenheit, respectively.
  Initially, both TC and TF are empty. When the user enters a numerical
  value into TC the corresponding value in TF is automatically updated
  and vice versa. When the user enters a non-numerical string into TC
  the value in TF is not updated and vice versa. The formula for converting
  a temperature C in Celsius into a temperature F in Fahrenheit is
  C = (F - 32) * (5/9) and the dual direction is F = C * (9/5) + 32."
  (:require
   [reagent.core :as r]))

(defn F->C [F] (* (/ 5 9) (- F 32)))
(defn C->F [C] (+ (* C (/ 9 5)) 32))

(defn converter []
  (let [state (r/atom {:celsius nil
                       :fahrenheit nil})]
    (fn []
      (let [{:keys [celsius fahrenheit]}  @state]
        [:div
         [:<>
          [:input
           {:value (or celsius js/undefined)
            :on-change (fn [e]
                         (let [c (-> e .-target .-value)]
                           (reset! state {:celsius c
                                          :fahrenheit (if (or (= "" c)
                                                              (js/isNaN c))
                                                        fahrenheit
                                                        (C->F c))})))}]
          [:label "Celsius ="]
          [:input
           {:value (or fahrenheit js/undefined)
            :on-change (fn [e]
                         (let [f (-> e .-target .-value)]
                           (reset! state {:celsius (if (or (js/isNaN f))
                                                     celsius
                                                     (F->C f))
                                          :fahrenheit f})))}]
          [:label "Fahrenheit"]]]))))
