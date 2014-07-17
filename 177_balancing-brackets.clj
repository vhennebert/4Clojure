#(loop [m {\( \) \[ \] \{ \}} [c & r] % p []]
   (cond
     (m c) (recur m r (conj p (m c)))
     (#{\) \] \}} c) (if (= c (peek p)) (recur m r (pop p)))
     c (recur m r p)
     1 (empty? p)))
