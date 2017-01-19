(ns ep23deftype.scratch
  (:require [ep23deftype.binary-tree :refer [->Node]]))

(= [1 2 3] [1 2 3])  ;;=> true
(= 5 7)              ;;=> false

;; low level
(definterface ,,,)
(deftype ,,,)

;; application level
(defprotocol ,,,)
(defrecord)

(definterface Coercible
  (to-string [])
  (to-int []))

(import 'ep23deftype.binary_tree.Node)

(Node. 17 nil nil)
;;=> #object[ep23deftype.binary_tree.Node 0x3a19e54 "ep23deftype.binary_tree.Node@3a19e54"]

(->Node 17 nil nil)
;;=> #object[ep23deftype.binary_tree.Node 0x4f442347 "ep23deftype.binary_tree.Node@4f442347"]

(def node (->Node 17 nil nil))

(.value node)

;; clojurescript
;;(.-value node)

(def EMPTY_TREE (Node. nil nil nil))

(conj EMPTY_TREE 1 2 3)

(def tree (into EMPTY_TREE (take 10 (repeatedly #(rand-int 100)))))

(= (into EMPTY_TREE [5 4 6 9]) (into EMPTY_TREE [5 4 6 9]))


(def *warn-on-reflection true)
