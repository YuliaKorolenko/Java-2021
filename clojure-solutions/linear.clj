; Duplicate code, refactor me
(defn unary [f] (fn [a b] (mapv f a b)))
(def v+ (unary +))
(def v- (unary -))
(def v* (unary *))
(def vd (unary /))
(def m+ (unary v+))
(def m- (unary v-))
(def m* (unary v*))
(def md (unary vd))

(defn scalar [a b] (reduce + (v* a b)))
(defn vect [[a0 a1 a2] [b0 b1 b2]]
      [(- (* a1 b2) (* b1 a2))
       (- (* b0 a2) (* a0 b2))
       (- (* a0 b1) (* a1 b0))])
(defn v*s [a, b] (mapv (fn [item] (* item b)) a))


(defn transpose [a] (apply mapv vector a))
(defn m [f] (fn [a b] (mapv (fn [item] (f item b)) a)))
(def m*s (m v*s))
(def m*v (m scalar))
(defn m*m [a, b] (mapv
                   (fn [item]
                       (mapv (fn [item2] (scalar item item2))
                             (transpose b)))
                   a))

(defn operationOfTensor [f] (fn recursiveOperation [a b] (if (vector? a)
                                              (mapv recursiveOperation a b)
                                              (f a b)
                                              )))
(def t+ (operationOfTensor +))
(def t- (operationOfTensor -))
(def t* (operationOfTensor *))
(def td (operationOfTensor /))