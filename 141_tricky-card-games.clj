#(fn [s]
   (let [g (group-by :suit s)]
     (last
       (sort-by :rank
                (if (g %)
                  (g %)
                  (g (:suit (first s))))))))
