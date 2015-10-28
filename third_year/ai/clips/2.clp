(deffacts f
    (students John Paul Marry)
    (one)
)

(defrule nice
    ?a<-(one)
    (students ?x $?rest)
    =>
    (printout t "The students are" ?x $?rest)
    (retract ?a)
)
