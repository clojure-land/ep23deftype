(ns ep23deftype.binary-tree)

(deftype Node [value left right]
  clojure.lang.ISeq
  (first [_] ,,,)
  (next [_] ,,,)
  (more [_] ,,,)
  (cons [_ x] ,,,))
