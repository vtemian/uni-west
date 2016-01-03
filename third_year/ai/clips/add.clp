(deffacts numbers
    (number 1)
    (number 2)
    (number 3)
    (number 4)
)

(defrule addup
    (number ?x)
    (number ?y)
    =>
    (bind ?total (+ ?x ?y))
    (printout t "total is " ?total crlf)
    (assert (total ?total))
)

(reset)
(run)
