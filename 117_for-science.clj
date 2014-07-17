#(let [l %
       G get-in
       s (comp range count)
       m (first
           (for [i (s l)
                 j (s (first l))
                 :when (= \M (G l [i j]))]
             [i j]))]
   (loop [l (mapv vec l) [m & r] [m]]
     (cond
       (= \C (G l m)) true
       (nil? m) false
       (#{\M \ } (G l m)) (recur (assoc-in l m \.)
                                 (concat
                                   (for [d [[-1 0] [1 0] [0 -1] [0 1]]
                                         :let [n (map + m d)]
                                         :when (#{\C \ } (G l n \#))] n)
                                   r))
       :else (recur l r))))
