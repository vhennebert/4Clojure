(fn [& c]
  (let [f (map first c)]
    (if (apply = f)
      (first f)
      (recur (map #(remove (partial > (apply max f)) %) c)))))
