(fn f [n p [x & r]]
  (let [n (if (p x) (dec n) n)]
    (if (= 0 n)
      ()
      (cons x (lazy-seq (f n p r))))))
