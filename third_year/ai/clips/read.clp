(deftemplate student1
    (slot name)
    (slot faculty)
    (slot year)
    (multislot grades)
)

(deffacts f
    (it 0)
)

(defrule readings
    ?a <- (it ?i&:(< ?i 10)&:(>= ?i 1))

    ;(test (< ?i 10))
    =>
    (bind ?x (read))
    (retract ?a)
    (assert (it (+ ?i 1)))
    (printout t ?x)
)
