((fn [t]
   (->> [[0 0 0] [1 1 1] [2 2 2] [0 1 2] [2 1 0]]
        (map #(map nth t %))
        (concat t)
        (some (fn [r] (some #(when (= [% % %] r) %) [:x :o])))))
