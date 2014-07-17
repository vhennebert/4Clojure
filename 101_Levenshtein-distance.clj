(fn [I x y]
  (last
    (reduce
      (fn [[i & r] y]
        (map first (reductions
                     (fn [[d s] [i x]] [(min (I d) (I i) (if (= x y) s (I s))) i])
                     [(I i) i] (map list r x))))
      (range (I (count x)))
      y))) inc
