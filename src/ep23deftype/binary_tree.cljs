(ns ep23deftype.binary-tree)

(deftype Node [value left right]
  ISeqable
  (-seq [this] this)

  ISeq
  (-first [_] (if left
               (first left)
               value))
  (-rest [this] (let [n (next this)]
                  (if (nil? n) () n)))

  INext
  (-next [_] (if left
               (if (.-left left)
                 (Node. value (next left) right)
                 (Node. value (.-right left) right))
               right))

  ICollection
  (-conj [this x] (cond
                   (nil? value)
                   (Node. x nil nil)

                   (= x value)
                   this

                   (< x value)
                   (Node. value
                          (if left
                            (conj left x)
                            (Node. x nil nil))
                          right)

                   (> x value)
                   (Node. value
                          left
                          (if right
                            (conj right x)
                            (Node. x nil nil)))))

  ICounted
  (-count [_]
    (if (nil? value)
      0
      (+ (if left (count left) 0)
         (if right (count right) 0))))

  IEquiv
  (-equiv [this o]
    (cond
      (identical? this o) true
      (or (seqable? o))
      (loop [me this
             you (seq o)]
        (if (nil? me)
          (nil? you)
          (and (= (first me) (first you))
               (recur (next me) (next you)))))
      :else false))

  IEmptyableCollection
  (-empty [_]
    (Node. nil nil nil))

  IPrintWithWriter
  (-pr-writer [coll writer opts]
    (pr-sequential-writer writer pr-writer "(" " " ")" opts coll)))
