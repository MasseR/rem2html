(ns remind.core
  (:require [clojure.string :as string]))

(def debugdata (slurp "debug.dat"))

(defn datetomap [old dt]
  ( let [date (nth dt 1)
         tm (or  (nth dt 2) :allday)
         msg (nth dt 4)]
    (merge-with (partial merge-with into) old {date {tm [msg]}})))
(defn parse [origin]
  (let [reg #"(\d{4}/\d{2}/\d{2}) (\d{1,2}:\d{1,2}(am|pm))? ?(.*)"
        parsed (map #(re-find reg %) ( string/split-lines origin))]
    (reduce datetomap {} parsed)))
