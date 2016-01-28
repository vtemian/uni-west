; this is not a complete program !!!
; it's just an explanation for
; the predicate field constraint ":"
(deffacts aaa
	(pile-size 6)
)
;(test (> ?size 1))

; can be replaced in LHS of rules with:

(defrule test1
	(pile-size ?size&:(> ?size 1)&:(< ?size 5))
	=>
	(printout t ?size crlf)
)