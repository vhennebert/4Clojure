(fn [n a b]
  (let [f #(let [m (quot (dec n) %)] (*' % m (inc m) 1/2))]
    (+' (f a) (f b) (-' (f (* a b))))))
