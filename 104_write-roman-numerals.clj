#(loop [n % r "" [i v & [x & _ :as d]] [\I \V \X \L \C \D \M]]
   (if (= 0 n)
     r
     (recur (quot n 10) (str (apply str ([[] [i] [i i] [i i i] [i v] [v] [v i] [v i i] [v i i i] [i x]] (mod n 10))) r) d)))
