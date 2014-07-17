#(loop [[[x y :as p] & r] (seq %) c #{}]
   (if p
     (recur (concat r (for [[w z] % :when (= y w)] [x z])) (conj c p))
     c))
