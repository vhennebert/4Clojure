(fn [e]
  (loop [[x & v] (first e) c #{} e e]
    (cond
      x (let [c (conj c x)
              {conn x, disconn nil} (group-by #(some #{x} %) e)
              n (remove c (flatten conn))]
          (recur (concat v n) c disconn))
      (seq e) false
      :else true)))
