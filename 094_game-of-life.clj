(fn [b]
  (let [r (count b)
        c (count (first b))
        n (reduce #(%2 %1)
                  (vec (repeat (+ r 2) (vec (repeat (+ c 2) 0))))
                  (for [i (range r) j (range c) :when (= \# (get-in b [i j]))
                        x [0 1 2] y [0 1 2] :when (not= [1 1] [x y])]
                    #(update-in % [(+ i x) (+ j y)] inc)))
        f (comp rest butlast)]
    (map (fn [r n] (apply str (map
                                #(condp = %2
                                   3 \#
                                   2 %1
                                   \ ) r (f n)))) b (f n))))
