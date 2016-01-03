(defrule duck
    (animal duck) =>
    (assert (sound-is quarck)))

(deffacts startup
    (animal duck)
    (animal cat)
    (animal dog)
    (plant tomatoe)
    (plant cucumber)
    (warm-blooded dog)
    (warm-blooded cat)
    (warm-blooded duck)
    (lays-eggs duck)
    (lays-eggs turtle)
    (child-of dog puppy)
    (child-of cat kitten)
    (child-of turtle hatchling)
)

(defrule animal
    (animal ?name)
    (warm-blooded ?name)
    (not (lays-eggs ?name))
    =>
    (printout t ?name " animal is mamal" crlf)
    (assert (mamal ?name))
)

(facts)

(defrule mamal
    (mamal ?name)
    (child-of ?name ?young)
    =>
    (printout t ?young " is a mamal" crlf)
    (assert (mamal ?young))
)

(defrule remove-mamal
    ?mamal <- (mamal ?)
    =>
    (printout t "retractig " ?mamal)
    (retract ?mamal)
)


(reset)
(run)
(facts)

(defrule are-lights-working
        (not (lights-working ?))
        =>
        (printout t "Are the car's lights working (yes or no)?")
        (assert (lights-working (read))))
