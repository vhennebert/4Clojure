(fn [t] (apply min
               (reduce #(let [c (concat [(first %1)] %1 [(last %1)])]
                          (map-indexed (fn [i x] (+ x (min (nth c i) (nth c (inc i)))))
                                       %2))
                       t)))
