(fn [c]
  (->>
    (map list (sort (set c)) (range))
    (partition-by #(apply - %))
    (map #(list (ffirst %) (first (last %))))))
