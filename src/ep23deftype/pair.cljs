;; ClojureScript
(ns ep23deftype.pair)

(deftype Pair [x y]
  Object
  (vectorLength [_]
    (js/Math.sqrt (+ (* x x) (* y y)))))

(.vectorLength (->Pair 2 3))         ;;=> 3.605551275463989
