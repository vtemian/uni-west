; example taken from [GR, 383(394)]
; it demonstrates the use of ~ (not) operator for building constraints
; also the use of the | (or) operator for building constraints
; also the use of the & (and) operator for building constraints
; a second use of the and operator is exemplified in the rule black-or-brown-hair-3

(deftemplate person
    (slot name)
    (slot hair)
)

(deffacts some-facts
    (person (name Lily) (hair blonde))
    (person (name Bill) (hair brown))
    (person (name Mary) (hair black))
    )

(defrule person-without-brown-hair
    (person (name ?n) (hair ~brown))	;the hair is not brown
    =>
    (printout t ?n " does not have brown hair" crlf)
    )

(defrule hair-brown-or-black
    (person (name ?n) (hair brown | black))	;the hair is brown or black
    =>
    (printout t ?n " has dark hair" crlf)
    )

(defrule hair-neither-brown-or-black-2
    (person (name ?n) (hair ?color&~brown&~black))	;the hair is neither brown nor black, we also need the color
    =>
    (printout t ?n " has " ?color " hair - 2" crlf)
    )

(defrule hair-brown-or-black-3
    (person (name ?n) (hair ?color&brown|black))	;the hair is brown or black, we also need the color to print it
    =>
    (printout t ?n " has " ?color " hair - 3" crlf)
    )

(reset)
(run)
