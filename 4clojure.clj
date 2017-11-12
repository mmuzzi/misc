; 38 - max
(def my-max (fn [& args]
  (reduce #(if (> %1 %2) %1 %2) args)))

(my-max 1 20 3 1e3)

; 32 - duplicate a sequence
(def duple (fn [list]
  (sort (reduce conj list list))))

; 107 - Simple closures
(def power-x-to-n (fn [x]
  (fn [n] (reduce * (take x (repeat n))))))

; 41 - drop every nth item
(def drop-nth (fn [lst n]
  (apply vector (keep-indexed #(if (not= 0 (rem (inc %1) n)) %2) lst))))

; 143 - dot product
(def dot-prod (fn [x y]
                (let [multi-vec (fn [x y]
                                  (map #(* (nth y (.indexOf x %)) %)
                                       x))]
                (reduce + (multi-vec x y)))))

; 143 - better solution
(def dot-prod2 (fn [x y]
                 (apply + (map * x y))))

; 118 - re-implement map . NOT WORKING YET
(def my-map (fn [func s]
              (if (= 1 (count s))
                (func (first s))
                (into [] (my-map func (rest s))))))

(my-map #(* 2 %) [1 2 3])


; 120 - sum of square of digits
                                        ; ugly as fuck

(def count-sum-square-digits(fn [lst]
  (let [num->digits (fn [n]
                      (->> n
                           (iterate #(quot % 10))
                           (take-while pos?)
                           (mapv #(mod % 10))
                           rseq))
        less-than-sum-square-digits? (fn [x]
                                       (< x
                                          (reduce + (map #(* % %) (num->digits x)))))]
    (reduce + (map #(if (less-than-sum-square-digits? %) 1 0) lst)))))


(def sum (fn [x y]
           (if (= x 0)
             y
             (sum (my-dec x) (my-inc y)))))

(def my-dec (fn [x]
              (sum x -1)))

(def my-inc (fn [x]
              (sum x 1)))

(sum 1 2)
