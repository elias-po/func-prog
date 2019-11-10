;; Task: Compute an average of the given students' marks, for the students, whose names end in 'a'.

(let [input {"Inese" 10 "Vasja" 8 "Petja" 4 "Natasha" 7 "Anja" 10 "Lauris" 6 "Sandra" 8 "Krišjanis" 9}]
    (defn get-map [] (map (fn [x] (if (re-matches #".*a$" (key x)) (val x) 0)) input))
)

(defn get-divisor [] (count (filter (fn [x](not= x 0)) (seq (get-map)))))
(println "Avg mark: " (double (/ (reduce + (get-map)) (get-divisor))))

;; or for lesser readability, this line instead of the last two ¯\_(ツ)_/¯
;; (println "Avg mark: " (double (/ (reduce + (get-map)) ((fn [] (count (filter (fn [x] (not= x 0)) (seq (get-map)))))))))