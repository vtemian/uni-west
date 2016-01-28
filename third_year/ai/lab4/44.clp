; example taken from [GR, 384(395)]
; it demonstrates the use of ~ (not) operator for building constraints
; also the use of the | (or) operator for building constraints
; also the use of the & (and) operator for building constraints

(deftemplate person
    (slot name)
    (slot hair)
    (slot eyes)
)

(deffacts somefacts
    (person (name Lily) (eyes blue) (hair blonde))
    (person (name Bill) (hair red) (eyes brown))
    (person (name Mary) (hair brown))
    )

(defrule complexeyehairmatch
    (person (name ?name1)
        	(eyes ?eyes1&blue|green)
        	(hair ?hair1&~black)
    )
    (person (name ?name2&~?name1)	; isn't the first person
            (eyes ?eyes2&~?eyes1)	; hasn't got the eyes of the first person
        	(hair ?hair2&red|?hair1) 	; hair is black or red
    )
    =>
    (printout t ?name1 " has " ?eyes1 " eyes and " ?hair1 " hair" crlf)
    (printout t ?name2 " has " ?eyes2 " eyes and " ?hair2 " hair" crlf)
 )

(reset)
(run)
