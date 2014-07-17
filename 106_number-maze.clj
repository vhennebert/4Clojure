#(loop [l 1 n #{%1} v n]
   (if (n %2)
     l
     (recur (inc l)
            (into #{} (for [x n o (if (odd? x) [* +] [* + /]) :when (not (v (o x 2)))]
                        (o x 2)))
            (into v n))))
