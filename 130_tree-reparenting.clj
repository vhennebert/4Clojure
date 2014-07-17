(fn f [n [h & c :as t] & p]
  (cond
    (= n h) (concat t p)
    (seq c) (some identity
                  (for [i (range (count c))]
                    (let [[b [c & a]] (split-at i c)]
                      (f n c (concat [h] b a p)))))))
