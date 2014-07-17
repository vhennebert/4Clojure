(fn [n]
  (loop [n n s #{}]
    (cond
      (= 1 n) true
      (s n) false
      :else (recur (->> n
                        str
                        (map #(- (int %) (int \0)))
                        (map #(* % %))
                        (apply +))
                   (conj s n)))))
