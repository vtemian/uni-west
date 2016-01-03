(deftemplate personal-data
    (slot name)
    (slot age)
    (slot weight)
    (slot smoker)
    (multislot date_of_birth)
)

(deffacts people
    (personal-data (name adam) (weight 60) (age 30)
        (smoker no) (date-of-birth 18 06 1970))
    (personal-data (name brenda) (weight 120) (age 45)
        (smoker yes) (date-of-birth 18 06 1955))
    (personal-data (name charles) (weight 120) (age 60)
        (smoker yes)(date-of-birth 18 06 1940))
)

(deffacts data
    (date 18 06 2000)
)
