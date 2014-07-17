(fn [s] (let [c (fn [w x]
                  (condp <= (- (count w) (count x))
                    2 false
                    0 (some identity
                            (for [n (range (count w))]
                              (and (.startsWith x (subs w 0 n))
                                   (.endsWith x (subs w (inc n))))))
                    (recur x w)))]
          (loop [r (for [w s] [[w] (disj s w)])]
            (cond
              (empty? r) false
              (= s (set (ffirst r))) true
              :else (recur (for [[[w & _ :as ch] s] r
                                 x (filter #(c % w) s)]
                             [(cons x ch) (disj s x)]))))))
