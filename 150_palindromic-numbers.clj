(fn [x]
  (let [tp #(int (Math/pow 10 %))
        w (count (str x))
        p (quot (+ w 1) 2)
        n (- p (mod w 2))
        f (fn f [s n p m]
            (cond
              (< s m) (cons
                        (Long/parseLong (str s (apply str (rseq (vec (take n (str s)))))))
                        (lazy-seq (f (+ s 1) n p m)))
              (< n p) (f (/ m 10) (+ n 1) p m)
              1 (f m n (+ p 1) (* m 10))))]
    (remove #(< % x) (f (quot x (tp n)) n p (tp p)))))
