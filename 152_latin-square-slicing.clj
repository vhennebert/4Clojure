(fn [b]
  (let [M (apply max (map count b))]
    (->>
      (reduce
        (fn [[s m] v]
          (reduce
            #(reduce (fn [[s m] [[l o h] v]]
                       (if (= l h)
                         [(conj s v) m]
                         [s (update-in m [l o h] conj v)]))
                     %1 %2)
            [s {}]
            (for [w [(count v)]
                  l (range 2 (+ w 1))
                  o (range (- M w -1))
                  i (range (- w l -1))
                  :let [o (+ o i)
                        v (subvec v i (+ i l))]
                  :when (apply distinct? v)]
              (cons
                [[l o 1] [v]]
                (for [[h ps] (get-in m [l o])
                      p ps]
                  [[l o (+ h 1)] (conj p v)])))))
        [[] {}]
        b)
      first
      distinct
      (filter
        #(apply =
                (concat
                  (map set %)
                  (apply map sorted-set %))))
      (group-by count)
      (map (fn [[k v]] [k (count v)]))
      (into {}))))
