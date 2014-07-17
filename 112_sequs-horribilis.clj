(fn [C n c]
  (((fn f [[x & t] s c]
      (if (or (< n s) (nil? x))
        [c s (C c)]
        (let [[r s m] (if (coll? x)
                        (f x s [])
                        [x (+ s x) (- n s x -1)])]
          (if (<= m 0) [c s (C c)] (f t s (conj c r))))))
    c 0 []) 0)) count
