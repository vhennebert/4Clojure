(fn [b]
  (let [C comp
        S (C range count)
        P inc
        I identity
        M mapv
        R (C vec rseq)
        T #(apply M vector %)
        b (M #(vec (for [i (range 9)] (not= 0 (bit-and % (bit-shift-left 1 i))))) b)]
    (->> (mapcat #(for [i (S %) j (S (first %))
                        [dc dy] [[P I] [(C P P) dec]]]
                    (loop [a 0 c 1 x 0 j j]
                      (if (every? I (for [y (range c)] (get-in % [(+ i x) (+ j y)])))
                        (recur (+ a c) (dc c) (P x) (dy j))
                        a)))
                 [b
                  (R (M R b))
                  (T (R b))
                  (R (T b))])
         (filter #(> % 2))
         sort
         last)))
