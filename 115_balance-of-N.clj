#(let [s (str %)
       n (/ (count s) 2)]
   (apply =
          (for [p ((juxt take take-last) n s)]
            (apply + (map int p)))))
