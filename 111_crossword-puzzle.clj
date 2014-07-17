(fn [w p]
  (boolean
    (some #(re-matches (re-pattern (apply str (replace {\_ \.} %))) w)
          (let [p (map #(remove #{\ } %) p)]
            (mapcat #(partition-by #{\#} %) (concat p (apply map str p)))))))
