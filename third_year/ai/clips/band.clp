(deffacts bands
    (member-of carbo a b c d)
    (member-of prodigy q w r)
)

(defrule members
    (member-of ?band $? ?member $?)=>
    (printout t ?member " was a member of " ?band crlf)
)

(reset)
(run)
