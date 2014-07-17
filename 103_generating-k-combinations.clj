(fn f [n s]
  (condp < 1
    (- (count s) n -1) #{}
    n #{#{}}
    (reduce
      (fn [c x]
        (into
          (into c (f n (disj s x)))
          (map #(conj % x) (f (dec n) (disj s x)))))
      #{} s)))
