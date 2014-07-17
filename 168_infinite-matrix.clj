(fn i
  ([f] (i f 0 0))
  ([f m n]
   (let [r (fn r [f i] (lazy-cat [(f i)] (r f (+ i 1))))]
     (r #(r (partial f %) n) m)))
  ([f m n s t]
   (map #(take t %) (take s (i f m n)))))
