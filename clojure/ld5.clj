(defn getCycleLength [key] (- (* key 2) 2))
(defn substepPos [key row] (- (getCycleLength key) (* row 2)))
(defn getRoundedUpWidth [input key] (int (Math/ceil (/ (count input) (getCycleLength key))))) ;; width rounded UP   = how many cycles
(defn getWidth [input key] (int (Math/floor (/ (count input) (getCycleLength key))))) ;; width rounded DOWN = how many full cycles
(defn getRowNumber [key cyclePos] (if (> cyclePos key) (- key (- cyclePos key)) cyclePos))
(defn getLastCycleLength [input key] (rem (count input) (getCycleLength key)))
(defn getY [input key cyclePos]                             ;; y = how many symbols to be added to the rounded DOWN width on the current row (which is indicated by the cyclePos)
  (let [lcl (getLastCycleLength input key)]
    (if (> lcl key)                                         ;; if the last cycle is long enough to cross one row more than once
      (if (<= (getRowNumber key lcl) (getRowNumber key cyclePos)) 2 1) ;; <= because it's going up
      (if (>= (getRowNumber key lcl) (getRowNumber key cyclePos)) 1 0) ;; >= because it's going down
      )
    ))

(defn getRowWidth
  [input key cyclePos widthMultiplier]
  (+ (* (getWidth input key) widthMultiplier) (getY input key cyclePos)))

(defn encryptRow
  [input key row]
  (str
    (first input)
    (if
      (and (not= row 0) (not= row (- key 1)) (>= (count input) (substepPos key row)))
      (get input (substepPos key row)))
    (if (> (count input) (getCycleLength key))
      (encryptRow (subs input (getCycleLength key)) key row))
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


(defn getDownOffsetChange
  [input key cycle cyclePos]
  (cond
    (= (getRowNumber key cyclePos) 1) (+ (- (getRowWidth input key cyclePos 1) cycle) (- (* cycle 2) 1))
    (and (> (getRowNumber key cyclePos) 1) (< (getRowNumber key cyclePos) key)) (- (getRowWidth input key cyclePos 2) (- cycle 1)) ;; refactored formula here!
    (= (getRowNumber key cyclePos) key) (+ (- (getRowWidth input key cyclePos 2) (- (* cycle 2) 1)) cycle)
    ))

(defn getUpOffsetChange
  [input key cycle cyclePos]
  (cond
    (= (getRowNumber key cyclePos) key) (+ (+ cycle 1) (- (getRowWidth input key cyclePos 2) (* cycle 2)))
    (< (getRowNumber key cyclePos) key) (+ (* cycle 2) (- (getRowWidth input key cyclePos 2) (* cycle 2)))
    ))

(defn getOffsetChange
  [input key cycle cyclePos]
  (if (> cyclePos (- key 1))
    (- (getUpOffsetChange input key cycle cyclePos))
    (getDownOffsetChange input key cycle cyclePos)))

(defn decryptCycle
  [input key cycle cyclePos offset]
  (str
    (subs input (- offset 1) offset)
    (if (and (< cyclePos (getCycleLength key)) (<= (+ offset (getOffsetChange input key cycle cyclePos)) (count input)))
      (decryptCycle input key cycle (+ cyclePos 1) (+ offset (getOffsetChange input key cycle cyclePos))))))

(defn bootstrapDecryption
  [input key cycle]
  (if (<= cycle (getRoundedUpWidth input key))
    (str (decryptCycle input key cycle 1 cycle) (bootstrapDecryption input key (+ cycle 1)))
    )
  )

(defn decrypt
  [input key]
  (bootstrapDecryption input key 1))

(let [input "1234567890" key 3]
  (let [todecrypt (encrypt input key)]
    (println todecrypt)
    (println (decrypt todecrypt key))
    )
  )
