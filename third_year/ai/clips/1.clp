(deffacts one
    (students John Marry Paul)
)

(defrule two
    (students ?x $?rest) => (printout t "the students are" ?x $?rest)
)
