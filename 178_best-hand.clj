(fn [c]
  (let [s first
        r second
        g group-by
        k (fn [n] (filter #(= n (count (second %))) (g r c)))
        nk (comp seq k)
        f (= 1 (count (g s c)))
        s (fn [v]
            (let [s (->> c
                         (map #(.indexOf v (r %)))
                         set
                         sort)]
              (= 5 (- (last s) (first s) -1) (count s))))
        v (vec "A23456789TJQKA")
        st (or (s (subvec v 1)) (s v))] 
    (cond
      (and st f) :straight-flush
      (nk 4) :four-of-a-kind
      (and (nk 3) (nk 2)) :full-house
      f :flush
      st :straight
      (nk 3) :three-of-a-kind
      (= 2 (count (k 2))) :two-pair
      (nk 2) :pair
      1 :high-card)))
