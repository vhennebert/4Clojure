(fn [f]
  (->> (loop [l 1
              [pis [is & ris :as iss]]
              [{}
               (->> f
                    (map #(reduce (fn [m v] (conj m (if (% v) 1 0))) [] (take (count (first f)) '(A B C D))))
                    (group-by #(count (filter #{1} %)))
                    (sort-by first)
                    (map (fn [[_ ms]] (reduce #(assoc % %2 #{%2}) {} ms))))]]
         (if (empty? iss)
           pis
           (recur
             (inc l)
             (first
               (reduce (fn [[[pis is] is1] is2]
                         (let [[npi nis]
                               (reduce (fn [[npi is] [i ms i1 i2]]
                                         [(into npi [i1 i2])
                                          (update-in is [i] #(into ms %))])
                                       [[] {}]
                                       (for [[i1 ms1] is1 [i2 ms2] is2
                                             :let [i (loop [[v1 & r1] i1 [v2 & r2] i2 d 0 i []]
                                                       (cond
                                                         v1
                                                         (cond
                                                           (= v1 v2) (recur r1 r2 (if (= 2 v1) (inc d) d) (conj i v1))
                                                           (and (not-any? #{2} [v1 v2]) (< d l)) (recur r1 r2 (inc d) (conj i 2)))
                                                         (<= d l) i))]
                                             :when i]
                                         [i (into ms1 ms2) i1 i2]))]
                           [[(apply dissoc pis npi) (conj is nis)] is2]))
                       [[(into pis iss) []] is]
                       ris)))))
       (reduce (fn [c [pi ms]] (reduce (fn [c m] (update-in c [m] #(conj % pi))) c ms)) {})
       (filter #(= 1 (count (second %))))
       (map (comp first fnext))
       (map #(reduce (fn [m [v s]] (if (= 2 v) m (conj m (s v)))) #{} (map list % '([a A] [b B] [c C] [d D]))))
       set))
