(fn [g w]
  (let [b ({'w 'b 'b 'w} w)
        g #(get-in g %)
        R range
        r (R 4)]
    (into {}
          (for [x r y r
                :when (= 'e (g [x y]))
                :let [c [x y]
                      r (R -1 2)
                      s (reduce
                          into #{}
                          (for [i r j r :when (not= [0 0] [i j])]
                            (loop [f #(map + [i j] %) c (f c) s []]
                              (condp = (g c)
                                b (recur f (f c) (conj s c))
                                w s
                                []))))]
                :when (seq s)]
            {c s}))))
