(deftemplate student
    (slot name)
    (slot faculty)
    (slot year)
    (multislot grades)
)

(deffacts students
    (student (name Vlad) (faculty yep) (year 2013) (grades 1 2 3 4))
)

(defrule birthday
    ?birthday <- (birthday ?name)
    ?person <- (student (name ?name) (year ?age)) =>
    (modify ?person (year (+ ?age 1)))
    (retract ?birthday)
)

(reset)
(run)
(facts)

(assert (birthday Vlad))
