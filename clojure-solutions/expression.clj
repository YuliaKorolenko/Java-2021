(defn constant [a] (fn [map] a))
(defn variable [name] (fn [map] (map name)))

(defn unaryOperation [f] (fn [a] (fn [map] (f (a map)))))
(def negate (unaryOperation -))
(def sinh (unaryOperation #(Math/sinh %)))
(def cosh (unaryOperation #(Math/cosh %)))

(def d (fn [a b] (/ (double a) (double b))))
(defn binaryOperation [f] (fn [a b] (fn [map] (f (a map) (b map)))))
(def multiply (binaryOperation *))
(def divide (binaryOperation d))
(def subtract (binaryOperation -))
(def add (binaryOperation +))

(def negate (unaryOperation -))
(def sinh (unaryOperation #(Math/sinh %)))
(def cosh (unaryOperation #(Math/cosh %)))

(def mapOfOperationF {'+ add, '- subtract, '* multiply, '/ divide, 'sinh sinh, 'cosh cosh, 'negate negate})
(defn parseF [a] (cond
                  (number? a) (constant a)
                  (symbol? a) (variable (str a))
                  :else (apply (mapOfOperationF (nth a 0)) (map parseF (rest a)))
                  ))
(defn parseFunction [a] (parseF (read-string a)))

(load-file "proto.clj")

(def evaluate (method :evaluate))
(def diff (method :diff))
(def toString (method :toString))
(def value (field :value))
(def name (field :name))
(def args (field :args))

(defn constructorOfPrototype [evaluate, diff, toString] {:evaluate evaluate, :diff diff, :toString toString})

(def ZERO)
(def ONE)
(def Constant (constructor (fn [this val]
                               (assoc this
                                      :value val)), (constructorOfPrototype (fn [this, mapa] (value this))
                                                                            (fn [this name] ZERO)
                                                                            (fn [this] (format "%.1f" (double (value this))))
                                                                            )))
(def ZERO (Constant 0))
(def ONE (Constant 1))
(def Variable (constructor (fn [this name]
                               (assoc this
                                      :name name)), (constructorOfPrototype (fn [this, mapa] (mapa (name this)))
                                                                            (fn [this var] (if (= var (name this)) ONE ZERO))
                                                                            (fn [this] (str (name this)))
                                                                            )))


(defn Operations [operator, strOfOp, funcOfOp] (constructor (fn [this & args]
                                                                (assoc this
                                                                       :args args)), (constructorOfPrototype (fn [this, mapa] (apply operator (mapv (fn [x] (evaluate x mapa)) (args this))))
                                                                                                             (fn [this name] (funcOfOp (args this), (mapv (fn [x] (diff x name)) (args this))))
                                                                                                             (fn [this] (str "(" strOfOp " "
                                                                                                                             (clojure.string/join " " (mapv (fn [x] (toString x)) (args this)))
                                                                                                                             ")"))
                                                                                                             )))

(def Add (Operations +,
                     "+",
                     (fn [[x, y], [dx, dy]] (Add dx dy))))
(def Subtract (Operations -,
                          "-",
                          (fn [[x, y], [dx, dy]] (Subtract dx dy))))
(def Multiply (Operations *,
                          "*",
                          (fn [[x, y], [dx, dy]] (Add (Multiply dx y) (Multiply dy x)))))

(def Divide (Operations d,
                        "/",
                        (fn [[x, y], [dx, dy]] (Divide
                                                 (Subtract
                                                   (Multiply dx y)
                                                   (Multiply dy x))
                                                 (Multiply y y)))))
(def Negate (Operations -,
                        "negate",
                        (fn [[x], [dx]] (Negate dx))))


(def Cosh)
(def Sinh (Operations #(Math/sinh %),
                      "sinh",
                      (fn [[x], [dx]] (Multiply dx (Cosh x)))))

(def Cosh (Operations #(Math/cosh %),
                      "cosh",
                      (fn [[x], [dx]] (Multiply dx (Sinh x)))))

(def mapOfOperation {'+ Add, '- Subtract, '* Multiply, '/ Divide, 'sinh Sinh, 'cosh Cosh, 'negate Negate})
(defn parse [a] (cond
                  (number? a) (Constant a)
                  (symbol? a) (Variable (str a))
                  :else (apply (mapOfOperation (nth a 0)) (map parse (rest a)))
                  ))
(defn parseObject [a] (parse (read-string a)))