(defn countCycle [key] (- (* key 2) 2))
(defn substepPos [key row] (- (countCycle key) (* row 2)))

(defn encryptRow
  [input key row]
  (str
    (first input)
    (if
      (and (not= row 0) (not= row (- key 1)) (>= (count input) (substepPos key row)))
      (get input (substepPos key row)))
    (if (> (count input) (countCycle key))
      (encryptRow (subs input (countCycle key)) key row))
    )
  )

(defn bootstrapEncryption
  [input key position]
  (if (< position key)
    (str (encryptRow (subs input position) key position) (bootstrapEncryption input key (+ position 1))))
  )

(defn encrypt
  [input key]
  (bootstrapEncryption (clojure.string/replace input " " "_") key 0))


(let [input "1234567890"]
  (println (encrypt input 3))
  )
