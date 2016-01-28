; example taken from [GR, page 378(382)]
; it demonstrates a multiple match of a fact

(deftemplate person
    (slot name)
    (multislot children))

(deffacts some-persons
    (person (name "John Doe")
        (children Joe Joe Joe))
    (find-child Joe)
    )

(defrule find-child
    (find-child ?child)
    (person (name ?name)
        	(children $?before ?child $?after)
        )
    =>
    (printout t ?name " has child " ?child crlf)
    (printout t "other children are " ?before " " ?after crlf)
)

(reset)
(run)