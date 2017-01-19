(ns ep23deftype.pair)

(deftype Pair [x y]
  java.lang.Object
  (equals [me you]
    (and (instance? Pair you)
         (= x (.x you))
         (= y (.y you)))))


;; without custom `equals`
(= (->Pair 4 5) (->Pair 4 5))          ;;=> false

;; with custom `equals`
(= (->Pair 4 5) (->Pair 4 5))          ;;=> true
