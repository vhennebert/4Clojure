(fn [a]
  (->>
    (for [q (a :accepts)] [q ""])
    (iterate #(for [[q w] %
                    [p t] (a :transitions)
                    [s r] t :when (= q r)]
                [p (str s w)]))
    (map #(list (for [[q w] % :when (= (a :start) q)] w)
                (seq %)))
    (take-while second)
    (mapcat first)
    (remove empty?)))
