(ns ep23deftype.binary-tree)

(deftype Node [value left right]
  clojure.lang.Sequential

  clojure.lang.Seqable
  (seq [this] this)

  clojure.lang.ISeq
  (first [_]
    (if left
      (first left)
      value))

  (next [_]
    (if left
      (if (.left left)
        (->Node value (next left) right)
        (->Node value (.right left) right))
      right))

  (more [this]
    (let [n (next this)]
      (if (nil? n) () n)))

  (cons [this x]
    (cond
      (nil? value)  (->Node x nil nil)
      (= x value)   this

      (> value x)
      (->Node value
              (if left
                (conj left x)
                (->Node x nil nil))
              right)

      (< value x)
      (->Node value
              left
              (if right
                (conj right x)
                (->Node x nil nil)))))

  clojure.lang.IPersistentCollection
  (count [_]
    (if (nil? value)
      0
      (+ (if left (count left) 0)
         (if right (count right) 0))))

  (empty [_]
    (Node. nil nil nil))

  (equiv [this o]
    (cond
      (identical? this o)
      true

      (or (instance? clojure.lang.Sequential o) (instance? java.util.List o))
      (loop [me this
             you (seq o)]
        (if (nil? me)
          (nil? you)
          (and (clojure.lang.Util/equiv (first me) (first you))
               (recur (next me) (next you)))))

      :else false)))
